<%@page import="java.util.*"%>
<%
    HashMap<String, String> listeCss= (HashMap<String, String>) request.getAttribute("cssVar");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="listeCss" method="post">
        <%
            for (Map.Entry<String, String> entry : listeCss.entrySet()) {
                String cle = entry.getKey();
                String valeur = entry.getValue();
            %>
                <label for="">
                    <%
                        out.println(cle);
                    %>
                </label>
                <input type="text" name="valeur" id="" value=<% out.println(valeur); %>>
                <input type="hidden" name="cle" value=<% out.println(cle); %>>
            <br>
        <% }
        %>
        <input type="submit" value="Modifier">
    </form>
    <h2>
        Nouvelle Variable
    </h2>
    <form action="listeCss" method="post" id="cssForm">
        <div id="dynamicInputs">
            <!-- Les champs de saisie existants -->
            <input type="text" name="name" id="" placeholder="Nom de la variable">
            <br>
            <input type="text" name="value" id="" placeholder="Valeur">
        </div>
        <button type="button" onclick="ajouterCss()">
            Ajouter une variable CSS
        </button>
        <br>
        <input type="submit" value="valider">
    </form>
    
    <script>
        function ajouterCss() {
            // Création de nouveaux champs de saisie
            var dynamicInputsDiv = document.getElementById("dynamicInputs");
    
            var nouveauInputName = document.createElement("input");
            nouveauInputName.type = "text";
            nouveauInputName.name = "name";
            nouveauInputName.placeholder="Nom de la variable"
    
            var nouveauInputValue = document.createElement("input");
            nouveauInputValue.type = "text";
            nouveauInputValue.name = "value";
            nouveauInputValue.placeholder="Valeur de la variable"
    
            // Ajout des nouveaux champs à la div
            dynamicInputsDiv.appendChild(nouveauInputName);
            dynamicInputsDiv.appendChild(document.createElement("br"));
            dynamicInputsDiv.appendChild(nouveauInputValue);
            dynamicInputsDiv.appendChild(document.createElement("br"));
        }
    </script>
</html>