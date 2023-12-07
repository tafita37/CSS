package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import css.CssFile;

public class ServletListe
extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletContext context = getServletContext();
            File file=new File(context.getRealPath("style.css"));
            CssFile cssFile=new CssFile(file);
            HashMap<String, String> listeVarCss=cssFile.readContent();
            req.setAttribute("cssVar", listeVarCss);
            req.getRequestDispatcher("listeVariableCss.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter=resp.getWriter();
        String[] cles=req.getParameterValues("cle");
        String[] valeurs=req.getParameterValues("valeur");
        String[] names=req.getParameterValues("name");
        String[] values=req.getParameterValues("value");
        ServletContext context = getServletContext();
        File file=new File(context.getRealPath("style.css"));
        CssFile cssFile=new CssFile(file);
        if(cles!=null&&cles.length!=0) {
            try {
                cssFile.changeCssValueOfVariable(cles, valeurs);
                resp.sendRedirect("listeCss");
            } catch (Exception e) {
                printWriter.println(e.getMessage());
                e.printStackTrace();
            }
        } 
        if(names!=null) {
            try {
                for(int i=0; i<names.length; i++) {
                    // System.out.println(names[i]);
                    cssFile.createVariable(names[i], values[i]);
                }
                resp.sendRedirect("listeCss");
            } catch (Exception e) {
                printWriter.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
