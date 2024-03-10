<%@page import="ma.ac.uir.model.Recette"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>





<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modifier la Recette</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

form {
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	padding: 20px;
	width: 80%; /* Utilisez un pourcentage au lieu d'une valeur fixe */
	max-width: 600px; /* Définissez une largeur maximale si nécessaire */
}

h2 {
	color: #333;
	text-align: center;
}

label {
	display: block;
	margin-bottom: 8px;
}

input, textarea {
	width: 100%;
	padding: 8px;
	margin-bottom: 16px;
	box-sizing: border-box;
	border: 1px solid #ddd;
	border-radius: 4px;
}

button {
	background-color: #4caf50;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.ingredient-checkbox {
	float: left;
	margin-right: 10px;
}

button:hover {
	background-color: #45a049;
}

.navbar {
    overflow: hidden;
    background-color: #333;
    display: flex;
    justify-content: space-between;
    position: fixed;
    top: 0;
    width: 100%;
}



.navbar a {
	display: inline-block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.navbar a:hover {
	background-color: #ddd;
	color: black;
}

.navbar-left, .navbar-right {
	display: flex;
}
</style>

<script type="text/javascript">
	
</script>

</head>
<body>
	<div class="navbar">
		<div class="navbar-left">
			<a href="connexion">Acceuil</a> <a href="ajouterRecetteForm">Ajouter</a>
			<a href="myRecipes"> Mes Recettes</a> <a href="favoris">Mes
				Favoris</a>
		</div>

		<div class="navbar-right">
			<s:if test="#session.user != null">
				<a href="<s:url action='afficherProfil'/>"><s:property
						value="#session.user.nom" /></a>
			</s:if>
			<a href="<s:url action='logout'/>">Déconnexion</a>
		</div>
	</div>

	<form action="mettreAJourRecetteSubmit" method="post" enctype="multipart/form-data">
		<h2>Modifier la Recette</h2>
		
		<img alt="<s:property value="recette.titre"/>" class="recette-img"
			src="data:image/jpeg;base64,${recipImage64encoded}"/>
		
		<label>Image de recette </label>
		
		<s:file name="uploadedFile" label="Image de la recette" />
		
		<input type="hidden" name="recetteId"
			value="<s:property value="recette.id" /> "> <label
			for="titre">Titre:</label> <input type="text" id="titre" name="titre"
			value="<s:property value="recette.titre"/>" required> <label
			for="description">Description:</label>
		<textarea id="description" name="description" rows="4" required><s:property
				value="recette.description" /></textarea>


		<label for="tempsPreparation">Temps de préparation (min):</label> <input
			type="number" id="tempsPreparation" name="tempsPreparation"
			value="<s:property value="recette.tempsPreparation"/>" required>


		<label for="categorie">Catégorie :</label> <select id="categorie"
			name="idCategorie" required>
			<s:iterator value="totalCategories" var="cat">
				<option value="<s:property value="#cat.id"/>"
					<s:if test="#cat.id == recette.categorie.id">
    					selected
					</s:if>>
					<s:property value="#cat.nom" />
				</option>
			</s:iterator>
		</select> Ingredients : <br>

		<ul>
			<!-- 				<li><input type="checkbox" name="ingredients"  -->
			<%-- 					value="<s:property value="#ingredient.id" />"> --%>
			<%-- 						<s:property value="#ingredient.nom" /> --%>
			<!-- 					</li> -->
			<s:iterator value="totalIngredients" var="ingredient">
				<li>
					<div class="ingredient-checkbox">
						<label for=" ingredient_<s:property value="#ingredient.id"/> ">

							<s:property value="#ingredient.nom" />
						</label> <input type="checkbox" name="idIngredients"
							id="ingredient_<s:property value="#ingredient.id"/>"
							value="<s:property value="#ingredient.id"/>"
							<s:iterator value="recette.ingredients" var="recIng">
                    		<s:if test="#recIng.id == #ingredient.id">
                        		checked
                    		</s:if>
            				</s:iterator> />
					</div>
				</li>
			</s:iterator>



		</ul>
		<!-- Ajoutez d'autres champs si nécessaire -->

		<button>Modifier</button>
	</form>

</body>
</html>

