<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Recette</title>
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
	    form {
	        background-color: white;
	        border-radius: 8px;
	        padding: 20px;
	        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	        width: fit-content;
	        margin-left: auto;
	        margin-right: auto;
	    }
	    div {
	        margin-bottom: 10px;
	    }
	    label {
	        display: block;
	        margin-bottom: 5px;
	        font-weight: bold;
	    }
	    input[type="text"],
	    input[type="number"],
	    textarea {
	        width: 100%;
	        padding: 8px;
	        border: 1px solid #ddd;
	        border-radius: 4px;
	        box-sizing: border-box; /* Ajoute cette propri�t� pour �viter le d�bordement */
	    }
	    input[type="submit"] {
	        background-color: #007bff;
	        color: white;
	        padding: 10px 20px;
	        border: none;
	        border-radius: 5px;
	        cursor: pointer;
	        display: block;
	        margin-top: 20px;
	        width: 100%;
	    }
	    input[type="submit"]:hover {
	        background-color: #0056b3;
	    }
	</style>
	<script>
	    function ajouterCategorie() {
	        var table = document.getElementById("tableCategories");
	        var row = table.insertRow(-1);
	        var cell1 = row.insertCell(0);
	        var cell2 = row.insertCell(1);
	        cell1.innerHTML = '<label>Cat�gorie:</label>';
	        cell2.innerHTML = '<input type="text" name="categories[]"/>';
	    }
	
	    function ajouterIngredient() {
	        var table = document.getElementById("tableIngredients");
	        var row = table.insertRow(-1);
	        var cell1 = row.insertCell(0);
	        var cell2 = row.insertCell(1);
	        var cell3 = row.insertCell(2);
	        cell1.innerHTML = '<label>Ingredient:</label>';
	        cell2.innerHTML = '<input type="text" name="ingredients[]"/>';
	        cell3.innerHTML = '<input type="text" name="quantites[]"/>';
	    }
	</script>
</head>
<body>
    <h2>Ajouter une Recette</h2>

    <form action="<s:url action='ajouterRecette'/>" method="post">
        <table style="width: fit-content; margin-left: auto; margin-right: auto; border-collapse: collapse;">
	        <tr>
	            <td style="padding: 10px; border: none;"><label for="titre">Titre:</label></td>
	            <td style="padding: 10px; border: none;"><input type="text" id="titre" name="titre"/></td>
	        </tr>
	        <tr>
	            <td style="padding: 10px; border: none; vertical-align: top;"><label for="description">Description:</label></td>
	            <td style="padding: 10px; border: none;"><textarea id="description" name="description"></textarea></td>
	        </tr>
	        <!-- Ajoutez ici des lignes pour les cat�gories si n�cessaire -->   
	        <tr>
	            <td style="padding: 10px; border: none;"><label for="tempsPreparation">Cat�gorie : </label></td>
	            <td style="padding: 10px; border: none;"><input type="number" id="tempsPreparation" name="tempsPreparation"/></td>
	            
	            <% 
	            /*TODO implementer la categorie probleme many to many
	        	* ul cases � cocher , et remplir depuis db
	            */
	            %>
	         
	        </tr>
	    </table>
	    
	    <!-- Tableau pour les cat�gories -->
	    <h3>Cat�gories</h3>
	    <table id="tableCategories" style="width: fit-content; margin-left: auto; margin-right: auto; border-collapse: collapse;">
	        <!-- Lignes ajout�es dynamiquement ici -->
	    </table>
	    <button type="button" onclick="ajouterCategorie()">Ajouter Cat�gorie</button>
	
	    <!-- Tableau pour les ingr�dients -->
	    <h3>Ingr�dients</h3>
	    <table id="tableIngredients" style="width: fit-content; margin-left: auto; margin-right: auto; border-collapse: collapse;">
	        <!-- Lignes ajout�es dynamiquement ici -->
	    </table>
	    <button type="button" onclick="ajouterIngredient()">Ajouter Ingr�dient</button>
	
	    <!-- Bouton de soumission du formulaire -->
	    <div style="text-align: center; margin-top: 20px;">
	        <input type="submit" value="Ajouter la recette"/>
	    </div>
    </form>
    
</body>
</html>