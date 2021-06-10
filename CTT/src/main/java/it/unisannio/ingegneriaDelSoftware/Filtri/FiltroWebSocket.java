package it.unisannio.ingegneriaDelSoftware.Filtri;

import javax.servlet.annotation.WebFilter;
import it.unisannio.ingegneriaDelSoftware.Util.Settings;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**Tramite questa annotazione si registra un filtro per le richieste in entrata verso la servlet con
 * uri specificato*/
@WebFilter
public class FiltroWebSocket implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**Le richieste consentite sono solo quelle provenienti dagli Ip pubblici registrati dei CTT*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Extract access token from the request
        String ip = servletRequest.getRemoteAddr();
        if (!Settings.trustedIp.contains(ip) && !Settings.ccsIp.equals(ip)) {
            returnForbiddenError(response, "Non sei un dipendente del CTT");
            return;
        }

        //è un dipendente
        filterChain.doFilter(
                new AuthenticatedRequest(
                        request, ip), servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    
    /**Blocca la request ed inoltra una risposta di errore al mittente
     * @param response Risposta Http
     * @param message Messaggio descrittivo
     * @throws IOException
     */
    private void returnForbiddenError(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }


    /**Inserisce all'interno della response l'IP del CTT come UserPrincipal in modo che possa recuperarlo lato websocket*/
    private static class AuthenticatedRequest extends HttpServletRequestWrapper {

    	/**Indirizzo IP*/
        private String ip;

        /**Metodo costruttore di AuthenticatedRequest
         * @param request Richiesta Http
         * @param ip Indirizzo ip
         */
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