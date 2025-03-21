package org.example.librarymanagement.controller;

import com.google.gson.Gson;
import org.example.librarymanagement.dao.BorrowDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/borrows")
public class BorrowServlet extends HttpServlet {

    private BorrowDAO borrowDAO;
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.borrowDAO = (BorrowDAO) context.getAttribute("borrowDAO");

        if (this.borrowDAO == null) {
            this.borrowDAO = new BorrowDAO();
            context.setAttribute("borrowDAO", this.borrowDAO);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long userId = Long.parseLong(req.getParameter("userId"));
            if (userId == null) throw new IllegalArgumentException("userId manquant");

            String jsonResponse = gson.toJson(borrowDAO.getBorrowedDocuments(userId));
            sendJsonResponse(resp, jsonResponse);
        } catch (Exception e) {
            sendErrorResponse(resp, "Erreur dans la récupération des emprunts : " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, Object> requestData = gson.fromJson(req.getReader(), Map.class);

            Long userId = ((Number) requestData.get("userId")).longValue();  // ✅ Convertir Double -> Long
            Long documentId = ((Number) requestData.get("documentId")).longValue();  // ✅ Convertir Double -> Long

            if (userId == null || documentId == null) throw new IllegalArgumentException("Données invalides");

            borrowDAO.borrowDocument(userId, documentId);
            sendJsonResponse(resp, "{\"message\":\"Document emprunté avec succès\"}");
        } catch (Exception e) {
            sendErrorResponse(resp, "Erreur lors de l'emprunt : " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, Object> requestData = gson.fromJson(req.getReader(), Map.class);

            Long userId = ((Number) requestData.get("userId")).longValue();  // ✅ Convertir Double -> Long
            Long documentId = ((Number) requestData.get("documentId")).longValue();  // ✅ Convertir Double -> Long

            if (userId == null || documentId == null) throw new IllegalArgumentException("Données invalides");

            borrowDAO.returnDocument(userId, documentId);
            sendJsonResponse(resp, "{\"message\":\"Document retourné avec succès\"}");
        } catch (Exception e) {
            sendErrorResponse(resp, "Erreur lors du retour du document : " + e.getMessage());
        }
    }

    private void sendJsonResponse(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }

    private void sendErrorResponse(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        sendJsonResponse(resp, "{\"error\": \"" + errorMessage + "\"}");
    }
}

