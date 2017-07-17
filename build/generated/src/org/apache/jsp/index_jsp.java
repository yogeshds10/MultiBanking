package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(7);
    _jspx_dependants.add("/WEB-INF/jspf/amenu.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/pending.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/bankmenu.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/branchmenu.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/staffmenu.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/custmenu.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/menu.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link href=\"style/superfish.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"style/main.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login - Simple Multi-Banking</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <h1>Simple Multi - Banking</h1>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"menu\">\n");
      out.write("                ");
try {
                        if (session.getAttribute("utype").toString().equals("admin")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"#\">View</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"viewbanks.jsp\">Banks</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");
      } else if (session.getAttribute("utype").toString().equals("user")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"profile.jsp\">Profile</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"passwd.jsp\">Change Password</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Tasks</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#\">Add</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"addbank.jsp\">Bank</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"#\">View</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewbanks.jsp\">Bank</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>");
      out.write("\n");
      out.write("                ");
      } else if (session.getAttribute("utype").toString().equals("bnk")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"profile.jsp\">Profile</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"passwd.jsp\">Change Password</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Tasks</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#\">Add</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"addaccount.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"#\">View</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewbanks.jsp\">Bank</a></li>\n");
      out.write("                    <li><a href=\"viewaccounts.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"viewbanks.jsp?own=1\">My Bank</a></li>\n");
      out.write("            <li><a href=\"viewbranch.jsp?own=1\">My Branches</a></li>\n");
      out.write("            <li><a href=\"viewaccounts.jsp?own=1\">My Accounts</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Transaction</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"deposit.jsp\">Deposit</a></li>\n");
      out.write("            <li><a href=\"withdraw.jsp\">Withdraw</a></li>\n");
      out.write("            <li><a href=\"summary.jsp\">Summary</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>");
      out.write("\n");
      out.write("                ");
      } else if (session.getAttribute("utype").toString().equals("brc")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"profile.jsp\">Profile</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"passwd.jsp\">Change Password</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Tasks</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#\">Add</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"addaccount.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"#\">View</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewbanks.jsp\">Bank</a></li>\n");
      out.write("                    <li><a href=\"viewaccounts.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"mybranch.jsp\">My Branch</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"addstaff.jsp?own=2&idbranch=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.idbranch}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Add Staff</a></li>\n");
      out.write("                    <li><a href=\"viewstaff.jsp?idbranch=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.idbranch}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">View Staff</a></li>\n");
      out.write("                    <li><a href=\"addaccount.jsp\">Add Customer</a></li>\n");
      out.write("                    <li><a href=\"viewaccounts.jsp?own=1\">View Customer</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Transaction</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"deposit.jsp\">Deposit</a></li>\n");
      out.write("            <li><a href=\"withdraw.jsp\">Withdraw</a></li>\n");
      out.write("            <li><a href=\"summary.jsp\">Summary</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("                ");
      } else if (session.getAttribute("utype").toString().equals("stf")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"profile.jsp\">Profile</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"passwd.jsp\">Change Password</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Tasks</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#\">Add</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"addaccount.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"#\">View</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewbanks.jsp\">Bank</a></li>\n");
      out.write("                    <li><a href=\"viewaccounts.jsp\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"mybranch.jsp\">My Branch</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewstaff.jsp?idbranch=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.idbranch}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">View Staff</a></li>\n");
      out.write("                    <li><a href=\"addaccount.jsp\">Add Customer</a></li>\n");
      out.write("                    <li><a href=\"viewaccounts.jsp?own=1\">View Customer</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Transaction</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"deposit.jsp\">Deposit</a></li>\n");
      out.write("            <li><a href=\"withdraw.jsp\">Withdraw</a></li>\n");
      out.write("            <li><a href=\"summary.jsp\">Summary</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("                ");
      } else if (session.getAttribute("utype").toString().equals("cus")) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"profile.jsp\">Profile</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"passwd.jsp\">Change Password</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"#\">Tasks</a>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#\">View</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"viewbanks.jsp\">Bank</a></li>\n");
      out.write("                    <li><a href=\"UserAccounts\">Account</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </li>\n");
      out.write("    <li><a href=\"LogoutHandler\">Logout</a></li>\n");
      out.write("</ul>");
      out.write("\n");
      out.write("                ");
      } else {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("<!--    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"#\">Contact</a></li>-->\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("                ");
      }
                } catch (Exception e) {
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("<ul class=\"sf-menu\">\n");
      out.write("<!--    <li><a href=\"home.jsp\">Home</a></li>\n");
      out.write("    <li><a href=\"#\">Contact</a></li>-->\n");
      out.write("</ul>\n");
      out.write("    \n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div id=\"content\">\n");
      out.write("                <p class=\"msg\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.msg}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("                <div class=\"login\">\n");
      out.write("                    <form action=\"LoginHandler\" method=\"post\">\n");
      out.write("                        <h2>Login</h2>\n");
      out.write("                        <label>Username : </label>\n");
      out.write("                        <input type=\"text\" name=\"uname\" id=\"uname\" /><br/><br/>\n");
      out.write("                        <label>Password : </label>\n");
      out.write("                        <input type=\"password\" name=\"passwd\" id=\"passwd\" /><br/><br/>\n");
      out.write("                        <div class=\"submit\">\n");
      out.write("                            <input type=\"submit\" value=\"Login\"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; \n");
      out.write("                            <input type=\"reset\"/>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"register\">\n");
      out.write("                    <h2>Register</h2>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"register.jsp?id=1\">Bank</a></li>\n");
      out.write("                        <li><a href=\"register.jsp?id=2\">User</a></li>\n");
      out.write("                        <li><a href=\"register.jsp?id=3\">Retail Customer</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"footer\">&COPY; 2013 All Rights Reserved.</div>\n");
      out.write("        </div>\n");
      out.write("        <script src=\"script/jquery-1.7.1.min.js\"></script>\n");
      out.write("        <script src=\"script/superfish.js\"></script>\n");
      out.write("        <script src=\"script/hoverIntent.js\"></script>\n");
      out.write("        <script src=\"script/validation.js\"></script>\n");
      out.write("        <script>\n");
      out.write("            $(function(){\n");
      out.write("                $(\"ul.sf-menu\").superfish(); \n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
