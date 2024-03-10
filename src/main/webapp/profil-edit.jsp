<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier Profil</title>
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
	
	    .buttons a, .buttons button {
	        padding: 10px 20px;
	        border-radius: 5px;
	        text-decoration: none;
	        color: white;
	        background-color: #007bff;
	        margin: 0 10px;
	        display: inline-block;
	        border: none;
	        cursor: pointer;
	    }
	
	    .buttons a:hover, .buttons button:hover {
	        background-color: #0056b3;
	    }
	</style>
</head>
<body>
    <h2>Modifier Profil</h2>

    <!-- Form for editing user information -->
    <form action="<s:url action='miseAJourProfil'/>" method="post">
        <table style="border-collapse: collapse; width: fit-content; margin-left: auto; margin-right: auto;">
            <tr>
                <td style="border: none; padding: 8px;"><strong>Nom :</strong></td>
                <td><input type="text" name="nom" style="border: none; padding: 8px;" value="<s:property value='%{utilisateur.nom}'/>"/></td>
            </tr>
            <tr>
                <td style="border: none; padding: 8px;"><strong>Email :</strong></td>
                <td><input type="email" name="email" style="border: none; padding: 8px;" value="<s:property value='%{utilisateur.email}'/>"/></td>
            </tr>
            <tr>
                <td style="border: none; padding: 8px;"><strong>Mot de passe :</strong></td>
                <td><input type="password" name="motDePasse" style="border: none; padding: 8px;" value="<s:property value='%{utilisateur.motDePasse}'/>"/></td>
            </tr>
            
        </table>
        <br>

        <!-- Buttons for saving the form -->
        <div class="buttons">
		    <button type="submit" class="button">Enregistrer</button>
		</div>
    </form>
</body>
</html>