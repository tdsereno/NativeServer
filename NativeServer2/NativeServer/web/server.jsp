<%-- 
    Document   : server
    Created on : 06/05/2016, 20:39:45
    Author     : Tiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Native Server</title>
    </head>
    <body>
        <h1>Exemplos de como usar o servidor NativeServer! versao 2.1</h1>
        <span><a href="/NativeServer/actions?acao=autenticar&user=Tiago&pass=123">Clique aqui para logar com o usuario Tiago</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=cadarvore&id=0&idespecie=1&idade=25&lat=33.333&longt=44.444&idpropietario=1&status=viva&geocode=Rua%20ABC,%20Lajeado">Clique aqui para inserir uma arvore de exemplo</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=cadarvore&id=2&idespecie=1&idade=30&lat=13133.333&longt=1244.444&idpropietario=1&status=morta&geocode=Rua%20ABC,%20LajeadoAlterado">Clique aqui para alterar a arvore id 2</a></span>

        <br>

        <span><a href="/NativeServer/actions?acao=listAllArvore">Clique aqui para listar todas arvores</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listArvore&id=1">Clique aqui para consultar uma arvore pelo id (passado por get)</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listAllEspecie">Clique aqui para listar todas especies</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listEspecie&id=1">Clique aqui para consultar uma especie pelo ID (passado por get)</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listAllSolicitacoes">Clique aqui para listar todas solicitacoes</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listAllTipoServico">Clique aqui para listar todis tipos de servico</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listAllProprietarios">Clique aqui para listar todos proprietarios</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=listProprietario&id=1">Clique aqui para consultar um proprietarios pelo id (passado por get)</a></span>
        <br>
        <span><a href="/NativeServer/actions?acao=cadProp&id=0&nome=tiago&identificacao=123.456.789-00&idcidade=0&endrua=rua&lat=99&long=33">Clique aqui para inserir um proprietario de exemplo</a></span>
        <br>
        



    </body>
</html>
