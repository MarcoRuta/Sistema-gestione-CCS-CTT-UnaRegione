package it.unisannio.ingegneriaDelSoftware.Filtri;

import it.unisannio.ingegneriaDelSoftware.Util.Settings;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**Tramite questa annotazione stiamo registrando un filtro per le richieste in entrata verso la servlet con
 * uri specificato*/
@WebFilter
public class FiltroWebSocket implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**Sovrascrivendo il metodo doFilter diamo una nostra implementazione del filtro.
     * Le richieste consentite sono solo quelle provenienti dagli Ip pubblici registrati dei CTT*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Extract access token from the request
        String ip = servletRequest.getRemoteAddr();
        if (!Settings.ip.containsValue(ip)) {
            returnForbiddenError(response, "Non sei un CTT");
            return;
        }

        //è un CTT
        filterChain.doFilter(
                new AuthenticatedRequest(
                        request, ip), servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**Non essendo un CTT blocco la request ed inoltro una risposta di errore al mittente*/
    private void returnForbiddenError(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }


    /**Se è un CTT, inserisco all'interno della response il suo Ip come UserPrincipal cosi che possa recuperarlo lato websocket*/
    private static class AuthenticatedRequest extends HttpServletRequestWrapper {

        private String ip;

        public AuthenticatedRequest(HttpServletRequest request, String ip) {
            super(request);
            this.ip = ip;
        }

        @Override
        public Principal getUserPrincipal() {
            return () -> ip;
        }
    }
}