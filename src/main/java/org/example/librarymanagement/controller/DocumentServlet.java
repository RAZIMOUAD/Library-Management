package org.example.librarymanagement.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.librarymanagement.config.DocumentInstanceCreator;
import org.example.librarymanagement.config.LocalDateAdapter;
import org.example.librarymanagement.dao.DocumentDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.librarymanagement.model.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/documents")
public class DocumentServlet extends HttpServlet {

    private  DocumentDAO documentDAO;
    private final Gson gson ;


    public DocumentServlet() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Document.class, new DocumentInstanceCreator()) // Ajout du gestionnaire d'héritage
                .create();
    }
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.documentDAO = (DocumentDAO) context.getAttribute("documentDAO");

        // Si documentDAO est null, on le crée manuellement pour éviter une erreur
        if (this.documentDAO == null) {
            this.documentDAO = new DocumentDAO();
            context.setAttribute("documentDAO", this.documentDAO);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (documentDAO == null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Service documentDAO indisponible\"}");
                return;
            }

            List<Document> documents = documentDAO.findAll();
            String jsonResponse = gson.toJson(documents);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            out.print(jsonResponse);
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Lire le JSON envoyé par le client
            BufferedReader reader = req.getReader();
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Convertir le JSON en objet Document avec l'adaptateur LocalDate
            Document document = gson.fromJson(jsonContent.toString(), Document.class);

            // Vérifier que les champs sont remplis
            if (document.getTitle() == null || document.getDateCreated() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Tous les champs sont obligatoires\"}");
                return;
            }

            // Sauvegarde en base
            documentDAO.save(document);

            // Réponse JSON
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Document ajouté avec succès\"}");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
