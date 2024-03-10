<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Recettes</title>
<!-- Ajoutez ici votre feuille de style CSS ou tout autre élément de décoration -->
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

h2 {
	color: #333;
	text-align: center;
}

.navbar {
    overflow: hidden;
    background-color: #333;
    display: flex;
    justify-content: space-between;
    position: fixed; /* Ajout de la propriété position: fixed pour fixer la barre de navigation en haut de la page */
    width: 100%; /* Ajustement de la largeur à 100% pour occuper toute la largeur du viewport */
    top: 0; /* Positionnement au sommet de la page */
    
    margin-bottom: 30px
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


table {
	border-collapse: collapse;
	width: 100%;
	/* Modification de la largeur à 100% pour s'assurer qu'elle occupe toute la largeur du conteneur parent */
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	margin-top: 20px;
	overflow-x: auto;
	padding-bottom : 20px;
	
	/* Ajout de la propriété overflow-x pour activer la barre de défilement horizontale si nécessaire */
}

th, td {
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4caf50;
	color: white;
}

tr:hover {
	background-color: #f5f5f5;
}

ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

ul li {
	display: inline-block;
	margin-right: 10px;
}

ul li a {
	text-decoration: none;
	color: #333;
}

ul li a:hover {
	color: #4caf50;
}
</style>
<script type="text/javascript">
	function editRecip(idRecette){
		document.getElementById("idInputr").value = idRecette;
		console.log("idRecette: "+ idRecette);
		console.log("idInputr : "+ document.getElementById("idInputr").value);
		document.getElementById("mettreAJourRecetteForm").submit();
	}
	
	function deleteRecip(idRecette,nomRecette){
		if(confirm("Voulez vous vraiment supprimer la recette "+nomRecette+" ?")){
			document.getElementById("idInputdel").value = idRecette;
			document.getElementById("deleteForm").submit();
		}
	}
</script>
</head>
<body>

<div class="navbar">
		<div class="navbar-left">
			<a href="/projet/connexion">Acceuil</a> <a href="ajouterRecetteForm">Ajouter</a> <a
				href="myRecipes"> Mes Recettes</a> <a href="favoris">Mes
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

	<h2>Mes recettes</h2>

	<table border="1">
		<tr>

			<th>Titre</th>
			<th>Description</th>
			<th>Temps de préparation (min)</th>
			<th>Catégorie</th>
			<th>Ingrédients de la recette</th>
			<th>Actions</th>
			<!-- Ajoutez d'autres colonnes si nécessaire -->
		</tr>

		<s:iterator value="myRecipes" var="recette">
			<tr>
				<td><s:property value="#recette.titre" /></td>
				<td><s:property value="#recette.description" /></td>
				<td><s:property value="#recette.tempsPreparation" /></td>
				<td><s:property value="#recette.categorie.nom" /></td>
				<td>
					<ul>
						<s:iterator value="#recette.ingredients" var="ingredient">
							<li><s:property value="#ingredient.quantite" /> de <s:property
									value="#ingredient.nom" /></li>
						</s:iterator>
					</ul>
				</td>
				<td>
					<ul>
						<li><a href="#" onclick="editRecip(<s:property value="#recette.id"/>)">Modifier</a></li>
						<li><a href="#" onclick="deleteRecip(<s:property value="#recette.id"/>,'<s:property value="#recette.titre"/>')">Supprimer</a></li>
					</ul>
				</td>
				<!-- Ajoutez d'autres colonnes si nécessaire -->
			</tr>
		</s:iterator>
	</table>
	
	<form action="mettreAJourRecetteForm" method="post" id="mettreAJourRecetteForm">
		<input type="hidden" id="idInputr" name="recetteId" >
	</form>
	
	<form action="deleteSubmit" method="post" id="deleteForm">
		<input type="hidden" id="idInputdel" name="recetteId" >
	</form>

	<!-- Ajoutez ici d'autres éléments de décoration ou de navigation si nécessaire -->

</body>
</html>
