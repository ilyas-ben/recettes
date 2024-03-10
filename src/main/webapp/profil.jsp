<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Profil Utilisateur</title>
    <style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f4f4f4;
	        margin: 0;
	        padding: 20px;
	        color: #333;
	    }
	    h2 {
	        color: #333;
	        text-align: center;
	    }
	    .user-info {
	        background-color: white;
	        border-radius: 8px;
	        padding: 20px;
	        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	        margin-bottom: 20px;
	        width: fit-content;
	        margin-left: auto;
	        margin-right: auto;
	    }
	    p {
	        margin: 10px 0;
	    }
	    .buttons {
	        text-align: center;
	    }
	    .buttons a {
	        padding: 10px 20px;
	        border-radius: 5px;
	        text-decoration: none;
	        color: white;
	        background-color: #007bff;
	        margin: 0 10px;
	        display: inline-block;
	    }
	    .buttons a:hover {
	        background-color: #0056b3;
	    }
	    .delete {
	        background-color: #dc3545;
	    }
	    .delete:hover {
	        background-color: #c82333;
	    }
	</style>
</head>
<body>
    <h2>Profil Utilisateur</h2>

    <!-- User Information -->
    <table style="border-collapse: collapse; width: fit-content; margin-left: auto; margin-right: auto;">
	    <tr>
	        <td style="border: none; padding: 8px;"><strong>Nom :</strong></td>
	        <td style="border: none; padding: 8px;"><s:property value="utilisateur.nom" /></td>
	    </tr>
	    <tr>
	        <td style="border: none; padding: 8px;"><strong>Email :</strong></td>
	        <td style="border: none; padding: 8px;"><s:property value="utilisateur.email" /></td>
	    </tr>
	    <tr>
	        <td style="border: none; padding: 8px;"><strong>Mot de passe :</strong></td>
	        <td style="border: none; padding: 8px;"><s:property value="utilisateur.motDePasse" /></td>
	    </tr>
	</table>
	<br>

    <!-- Modifier and Supprimer Buttons -->
    <div class="buttons">
        <a href="<s:url action='afficherProfilPourModif'/>" class="button">Modifier</a>
        <a href="<s:url action='supprimerCompte'/>" class="button delete">Supprimer</a>
    </div>
</body>
</html>