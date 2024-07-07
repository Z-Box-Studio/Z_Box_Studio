package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/videos")
public class VideosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File uploadDir = new File(UPLOAD_DIR);
        File[] files = uploadDir.listFiles();
        List<String> videos = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                videos.add(request.getContextPath() + "/" + UPLOAD_DIR + "/" + file.getName());
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("[");
        for (int i = 0; i < videos.size(); i++) {
            response.getWriter().write("{\"url\":\"" + videos.get(i) + "\"}");
            if (i < videos.size() - 1) {
                response.getWriter().write(",");
            }
        }
        response.getWriter().write("]");
    }
}
