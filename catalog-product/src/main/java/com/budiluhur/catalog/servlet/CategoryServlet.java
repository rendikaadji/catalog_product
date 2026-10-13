package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.model.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private final List<Category> categories = new ArrayList<>();

    @Override
    public void init() {
        categories.add(new Category(1, "Elektronik"));
        categories.add(new Category(2, "Aksesoris Komputer"));
        categories.add(new Category(3, "Peralatan Gaming"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Kategori</title></head><body>");
        out.println("<h2>Daftar Kategori</h2><ul>");
        for (Category c : categories) {
            out.println("<li>" + c.getId() + " - " + c.getName() + "</li>");
        }
        out.println("</ul></body></html>");
    }
}
