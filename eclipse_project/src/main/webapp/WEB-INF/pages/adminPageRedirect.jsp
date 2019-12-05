<!-- This JSP is a redirect used by the database modification methods to get back to the admin page -->
<%request.getRequestDispatcher("/admin").forward(request, response); %>