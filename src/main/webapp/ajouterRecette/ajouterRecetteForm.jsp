<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter Recette</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 50%;
	margin: 50px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 8px;
}

input, select {
	width: 100%;
	padding: 10px;
	margin-bottom: 16px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}

select {
	cursor: pointer;
}

.checkbox-group {
	margin-bottom: 16px;
}

.checkbox {
	margin-right: 10px;
}

button {
	background-color: #4caf50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.navbar {
	overflow: hidden;
	background-color: #333;
	display: flex;
	justify-content: space-between;
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
</head>
<body>
	<div class="navbar">
		<div class="navbar-left">
			<a href="connexion">Acceeuil</a> <a href="ajouterRecetteForm">Ajouter</a>
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
	<div class="container">
		<h2>Ajouter Recette</h2>
		<s:form action="ajouterRecetteSubmit" method="post" enctype="multipart/form-data">

			<s:file name="uploadedFile" label="Image de la recette" />

			<label for="titre">Titre:</label> <input type="text" id="titre"
				name="titre" required> <label for="description">Description:</label>
			<textarea id="description" name="description" rows="4" required></textarea>

			<label for="tempsPreparation">Temps de préparation (en
				minutes):</label> <input type="number" id="tempsPreparation"
				name="tempsPreparation" required> <label for="categorie">Catégorie:</label>
			<select name="idCategorie">
				<s:iterator value="totalCategories" var="categorie">
					<option value="<s:property value="#categorie.id"/>">
						<s:property value="#categorie.nom" />
					</option>
				</s:iterator>
			</select>

			<div class="checkbox-group">
				<s:iterator value="totalIngredients" var="ingredient">
					<li>
						<div class="ingredient-checkbox">
							<label for="ingredient_<s:property value="#ingredient.id"/>">
								<s:property value="#ingredient.nom" />
							</label> <input type="checkbox" name="idIngredients"
								id="ingredient_<s:property value="#ingredient.id"/>"
								value="<s:property value="#ingredient.id"/>" />
						</div>
					</li>
				</s:iterator>


			</div>
			<button type="submit">Ajouter Recette</button>
		</s:form>
	</div>
</body>
</html>

