package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Db.ArvoreDb;
import Db.CidadeDb;
import Db.EspecieDb;
import Db.ProprietarioDb;
import Db.SolicitacaoDB;
import Db.TipoServicoDb;
import Db.UsuarioDb;
import Model.Arvore;
import Model.Cidade;
import Model.Especie;
import Model.Proprietario;
import Model.Solicitacao;
import Model.TipoServico;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tiago
 */
@WebServlet(urlPatterns = {"/actions"})
public class actions extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;
    PrintWriter saida;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        requisicao = request;
        resposta = response;
        saida = response.getWriter();
        requisicao.setCharacterEncoding("utf-8");

        if (requisicao.getParameter("acao").equals("cadarvore")) {
            cadastrarArvore();
        } else if (requisicao.getParameter("acao").equals("cadProp")) {
            cadastrarProprietario();
        } else if (requisicao.getParameter("acao").equals("listAllArvore")) {
            listarArvores();
        } else if (requisicao.getParameter("acao").equals("autenticar")) {
            autenticarUsuario();
        } else if (requisicao.getParameter("acao").equals("listArvore")) {
            int id = 0;
            try {
                id = Integer.parseInt(requisicao.getParameter("id"));
                listArvores(id);
            } catch (Exception e) {
                saida.write("Não foi informado o parametro obrigatorio ID da arvore");
            }

        } else if (requisicao.getParameter("acao").equals("listAllEspecie")) {
            listarEspecies();
        } else if (requisicao.getParameter("acao").equals("listEspecie")) {
            int id = 0;
            try {
                id = Integer.parseInt(requisicao.getParameter("id"));
                listEspecie(id);
            } catch (Exception e) {
                saida.write("Não foi informado o parametro obrigatorio ID da especie");
            }
        } else if (requisicao.getParameter("acao").equals("listAllSolicitacoes")) {
            listarSolicitacoes();
        } else if (requisicao.getParameter("acao").equals("listAllTipoServico")) {
            listarTiposServico();

        } else if (requisicao.getParameter("acao").equals("listAllCidades")) {
            listarCidades();

        } else if (requisicao.getParameter("acao").equals("listAllProprietarios")) {
            listarProprietarios();
        } else if (requisicao.getParameter("acao").equals("listProprietario")) {
            int id = 0;
            try {
                id = Integer.parseInt(requisicao.getParameter("id"));
                listProprietarios(id);
            } catch (Exception e) {
                saida.write("Não foi informado o parametro obrigatorio ID do proprietarios");
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void cadastrarArvore() {
        String idarvore = requisicao.getParameter("id");

        String idespecie = requisicao.getParameter("idespecie");
        String idade = requisicao.getParameter("idade");
        String lat = requisicao.getParameter("lat");
        String longt = requisicao.getParameter("longt");
        String idpropietario = requisicao.getParameter("idpropietario");
        String idusuario = requisicao.getParameter("idusuario");
        String status = requisicao.getParameter("status");
        String geocode = requisicao.getParameter("geocode");
// chamada exemplo insert
        //   localhost:8080//NativeServer/actions?acao=cadarvore&id=0&idespecie=1&idade=25&lat=33.333&longt=44.444&idpropietario=1&idusuario=1&status=viva&geocode=Rua ABC, Lajeado
        Arvore v = new Arvore();
        v.setId(Integer.parseInt(idarvore));
        v.setEnderecoGeoCode(geocode);
        Especie especie = new Especie();
        especie.setId(Integer.parseInt(idespecie));
        v.setEspecie(especie);
        v.setIdade(Integer.parseInt(idade));
        v.setLatitude(lat);
        v.setLongitude(longt);
        Proprietario propietario = new Proprietario();
        propietario.setId(Integer.parseInt(idpropietario));
        v.setPropietario(propietario);
        v.setStatus(status);

        if (salvarArvore(v)) {
            saida.write("1 - Registro salvo com sucesso");
        } else {
            saida.write("2 - Erro ao cadastrar arvore");
        }
    }

    public boolean salvarArvore(Arvore v) {
        ArvoreDb db = new ArvoreDb();
        return db.salvar(v);

    }

    public void listarArvores() {
        ArvoreDb db = new ArvoreDb();
        ArrayList<Arvore> arvores = db.consultarTodas();
        if (arvores.size() > 0) {
            for (int i = 0; i < arvores.size(); i++) {

                saida.write("arvore");

                saida.write("@#" + arvores.get(i).getId());

                saida.write("@#" + arvores.get(i).getEspecie().getId());

                saida.write("@#" + arvores.get(i).getIdade());

                saida.write("@#" + arvores.get(i).getLatitude());

                saida.write("@#" + arvores.get(i).getLongitude());

                saida.write("@#" + arvores.get(i).getEnderecoGeoCode());

                saida.write("@#" + arvores.get(i).getPropietario().getId());

                saida.write("@#" + arvores.get(i).getStatus());
                ProprietarioDb pd = new ProprietarioDb();
                Proprietario p = new Proprietario();
                p.setId(arvores.get(i).getPropietario().getId());
                p = pd.consultarProprietario(p);
                saida.write("@#" + p.getNome());

                EspecieDb ed = new EspecieDb();
                Especie e = new Especie();
                p.setId(arvores.get(i).getEspecie().getId());
                e = ed.consultarEspecie(e);
                saida.write("@#" + e.getNome());

//                saida.write("________________");
//                saida.write("<br>");
//                saida.write("id :" + arvores.get(i).getId());
//                saida.write("<br>");
//                saida.write("especie id :" + arvores.get(i).getEspecie().getId());
//                saida.write("<br>");
//                saida.write("idade :" + arvores.get(i).getIdade());
//                saida.write("<br>");
//                saida.write("latitude :" + arvores.get(i).getLatitude());
//                saida.write("<br>");
//                saida.write("longitude :" + arvores.get(i).getLongitude());
//                saida.write("<br>");
//                saida.write("geocode :" + arvores.get(i).getEnderecoGeoCode());
//                saida.write("<br>");
//                saida.write("propietario id :" + arvores.get(i).getPropietario().getId());
//                saida.write("<br>");
//                saida.write("usuario modificador id :" + arvores.get(i).getUsuario().getId());
//                saida.write("<br>");
//                saida.write("status :" + arvores.get(i).getStatus());
//                saida.write("<br>");
            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }

    // lista uma arvore com base no id
    public void listArvores(int id) {
        ArvoreDb db = new ArvoreDb();
        Arvore av = new Arvore();
        av.setId(id);
        Arvore arvore = db.consultarArvore(av);
        if (arvore != null) {

            saida.write("arvore");

            saida.write("@#" + arvore.getId());

            saida.write("@#" + arvore.getEspecie().getId());

            saida.write("@#" + arvore.getIdade());

            saida.write("@#" + arvore.getLatitude());

            saida.write("@#" + arvore.getLongitude());

            saida.write("@#" + arvore.getEnderecoGeoCode());

            saida.write("@#" + arvore.getPropietario().getId());

            saida.write("@#" + arvore.getStatus());
            ProprietarioDb pd = new ProprietarioDb();
            Proprietario p = new Proprietario();
            p.setId(arvore.getPropietario().getId());
            p = pd.consultarProprietario(p);
            saida.write("@#" + p.getNome());

            EspecieDb ed = new EspecieDb();
            Especie e = new Especie();
            p.setId(arvore.getEspecie().getId());
            e = ed.consultarEspecie(e);
            saida.write("@#" + e.getNome());
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }

    }

    public void listarEspecies() {
        EspecieDb db = new EspecieDb();
        ArrayList<Especie> especies = db.consultarTodas();
        if (especies.size() > 0) {
            for (int i = 0; i < especies.size(); i++) {
                saida.write("especie");
                saida.write("@#" + especies.get(i).getId());
                saida.write("@#" + especies.get(i).getNome());
                saida.write("@#" + especies.get(i).getDscricao());
            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }

    public void listarSolicitacoes() {
        SolicitacaoDB db = new SolicitacaoDB();
        ArrayList<Solicitacao> solicitacao = db.consultarTodas();
        if (solicitacao.size() > 0) {
            for (int i = 0; i < solicitacao.size(); i++) {
                saida.write("solicitacao");

                saida.write("@#" + solicitacao.get(i).getId());

                saida.write("@#" + solicitacao.get(i).getUsuario().getId());

                saida.write("@#" + solicitacao.get(i).getProprietario().getId());
                saida.write("@#" + solicitacao.get(i).getDataSolicitacao());

                saida.write("@#" + solicitacao.get(i).getDataEncerramento());

                saida.write("@#" + solicitacao.get(i).getDescricao());

            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }

    public void listarProprietarios() {
        ProprietarioDb db = new ProprietarioDb();
        ArrayList<Proprietario> p = db.consultarTodas();
        if (p.size() > 0) {
            for (int i = 0; i < p.size(); i++) {
                saida.write("proprietario");

                saida.write("@#" + p.get(i).getId());

                saida.write("@#" + p.get(i).getNome());
                saida.write("@#" + p.get(i).getIdentificacao());
                saida.write("@#" + p.get(i).getEnderecoRua());
                CidadeDb cdb = new CidadeDb();
                Cidade city = cdb.consultarCidade(p.get(i).getCidade());

                saida.write("@#" + city.getId());

                saida.write("@#" + city.getNome());
            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }

    public void listarTiposServico() {
        TipoServicoDb db = new TipoServicoDb();
        ArrayList<TipoServico> tp = db.consultarTodas();
        if (tp.size() > 0) {
            for (int i = 0; i < tp.size(); i++) {
                saida.write("tiposervico");

                saida.write("@#" + tp.get(i).getId());

                saida.write("@#" + tp.get(i).getNome());

                saida.write("@#" + tp.get(i).getDescricao());

            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }

    private void listProprietarios(int id) {
        ProprietarioDb db = new ProprietarioDb();
        Proprietario pr = new Proprietario();
        pr.setId(id);
        Proprietario p = db.consultarProprietario(pr);

        if (p != null) {
            saida.write("proprietario");

            saida.write("@#" + p.getId());

            saida.write("@#" + p.getNome());

            saida.write("@#" + p.getIdentificacao());

            saida.write("@#" + p.getEnderecoRua());

            CidadeDb cdb = new CidadeDb();
            Cidade city = cdb.consultarCidade(p.getCidade());

            saida.write("@#" + city.getId());

            saida.write("@#" + city.getNome());
        }

    }

    private void listEspecie(int id) {
        EspecieDb db = new EspecieDb();
        Especie e = new Especie();
        e.setId(id);
        Especie especie = db.consultarEspecie(e);

        if (e != null) {
            saida.write("especie");

            saida.write("@#" + especie.getId());

            saida.write("@#" + especie.getNome());

            saida.write("@#" + especie.getDscricao());

        }

    }

    private void cadastrarProprietario() {
        String id = requisicao.getParameter("id");
        String nome = requisicao.getParameter("nome");
        String identificacao = requisicao.getParameter("identificacao");
        String endRua = requisicao.getParameter("endrua");
        String idcidade = requisicao.getParameter("idcidade");
        String lat = requisicao.getParameter("lat");
        String longi = requisicao.getParameter("long");

        Proprietario p = new Proprietario();
        p.setId(Integer.parseInt(id));
        p.setNome(nome);
        p.setIdentificacao(identificacao);
        p.setEnderecoRua(endRua);
        Cidade c = new Cidade();
        c.setId(Integer.parseInt(idcidade));
        p.setCidade(c);
        p.setLatitude(lat);
        p.setLongitude(longi);
        if (salvarProprietario(p)) {
            saida.write("1 - Registro salvo com sucesso");
        } else {
            saida.write("2 - Erro ao cadastrar arvore");
        }

    }

    public boolean salvarProprietario(Proprietario p) {
        ProprietarioDb db = new ProprietarioDb();
        return db.salvar(p);
    }

    private void autenticarUsuario() {
        UsuarioDb ud = new UsuarioDb();
        String login = requisicao.getParameter("user");
        String senha = requisicao.getParameter("pass");
        Usuario u = new Usuario();
        u.setLogin(login);
        u.setSenha(senha);
        int id = ud.autenticarUsuario(u);

        saida.write("" + id); // vai exibir o id do usuario caso a senha e user estejam corretos, caso contrario exibira "0"

    }

    private void listarCidades() {
        CidadeDb db = new CidadeDb();
        ArrayList<Cidade> tp = db.consultarTodas();
        if (tp.size() > 0) {
            for (int i = 0; i < tp.size(); i++) {
                saida.write("cidade");

                saida.write("@#" + tp.get(i).getId());

                saida.write("@#" + tp.get(i).getNome());

            }
        } else {
            saida.write("3 - Não foi localizado nenhum registro");
        }
    }
}
