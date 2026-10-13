package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.exception.ProductNotFoundException;
import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
        productRepository.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
        productRepository.addProduct(new Product("PRD-04", "Laptop Acer Predator Helios Neo 16", 20000000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

        out.println("<html><head><title>Katalog Produk Web</title></head><body>");
        if (id != null) {
            try {
                Product p = productRepository.findById(id);
                out.println("<h2>=== DETAIL PRODUK ===</h2>");
                out.println("<p>ID: " + p.getId() + "<br>Nama: " + p.getName()
                        + "<br>Harga: Rp" + formatPrice(p.getPrice()) + "</p>");
            } catch (ProductNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<h3 style='color:red'>Error 404: " + escape(e.getMessage()) + "</h3>");
            }
        } else {
            out.println("<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
            out.println("<table border='5' cellpadding='10' cellspacing='3'>");
            out.println("<tr><th>ID Produk</th><th>Nama Produk</th><th>Harga Satuan</th></tr>");
            for (Product p : productRepository.findAll()) {
                out.println("<tr><td>" + p.getId() + "</td><td>" + p.getName()
                        + "</td><td>Rp" + formatPrice(p.getPrice()) + "</td></tr>");
            }
            out.println("</table>");
        }
        out.println("</body></html>");
    }

    private static String formatPrice(double price) {
        return String.format(Locale.ROOT, "%.0f", price);
    }

    // id berasal dari query string -> escape agar tidak XSS
    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
