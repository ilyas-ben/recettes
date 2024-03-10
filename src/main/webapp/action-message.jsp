<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Action Status</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .message-container {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        text-align: center;
    }
    ul {
        list-style: none;
        padding: 0;
    }
    li {
        margin-bottom: 10px;
    }
    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        cursor: pointer;
    }
    a:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="message-container">
        <h2>Status update</h2>
        <!-- Display a contextual message -->
        <p><s:property value="message"/></p>
        
        <!-- Display contextual links -->
        <s:if test="%{actionContext == 'inscription-ok'}">
            <a href="index.jsp">Aller vers la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'inscription-!ok'}">
            <a href="inscription.jsp">Retour à la page d'inscription</a>
        </s:if>
        <s:if test="%{actionContext == 'connexion-!ok'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'miseAJourProfil-!ok'}">
            <a href="profil.jsp">Retour à la page de profil</a>
        </s:if>
        <s:if test="%{actionContext == 'miseAJourProfil-!ok-session'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'afficherProfil-!ok'}">
            <a href="profil.jsp">Retour à la page de profil</a>
        </s:if>
        <s:if test="%{actionContext == 'afficherProfil-!ok-session'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'supprimerCompte-ok'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'supprimerCompte-!ok'}">
            <a href="profil.jsp">Retour à la page de profil</a>
        </s:if>
        <s:if test="%{actionContext == 'supprimerCompte-!ok-session'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>
        <s:if test="%{actionContext == 'logout-!ok'}">
            <a href="main.jsp">Retour à la page d'acceuil</a>
        </s:if>
        <s:if test="%{actionContext == 'logout-!ok-session'}">
            <a href="index.jsp">Retour à la page de connexion</a>
        </s:if>

    </div>
</body>
</html>