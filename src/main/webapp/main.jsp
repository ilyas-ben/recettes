
<%@page import="org.hibernate.Hibernate"%>
<%@page import="org.hibernate.Session"%>
<%@page import="ma.ac.uir.dao.HibernateUtil"%>
<%@page import="java.util.Set"%>
<%@page import="ma.ac.uir.model.Recette"%>
<%@page import="ma.ac.uir.model.Utilisateur"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html lang="fr">
<head>

<meta charset="UTF-8">
<title>Accueil - Partage de Recettes</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
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

.content {
	padding: 20px;
	text-align: center;
}

.search-and-filter {
	display: flex;
	justify-content: space-between;
	margin-bottom: 20px;
}

.search-bar {
	display: flex;
}

.search-bar input[type="text"] {
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ddd;
	margin-right: 5px;
}

.search-bar button {
	padding: 10px 15px;
	border-radius: 5px;
	border: none;
	background-color: #007bff;
	color: white;
	cursor: pointer;
}

.search-bar button:hover {
	background-color: #0056b3;
}

.filter-options select {
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ddd;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 8px;
}

th {
	background-color: #f4f4f4;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.btn-favorites {
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	background-color: #4CAF50;
	color: white;
	cursor: pointer;
}

.btn-favorites:hover {
	background-color: #45a049;
}
</style>
<script type="text/javascript">
	function setId(idRecette){
		document.getElementById("idInputn").value = idRecette;
		document.getElementById("recetteDetailForm").submit();
	}
	
	function myRecipeOf(userId){
		document.getElementById("idInputr").value = userId;
		document.getElementById("myRecipesForm").submit();
	}
	
	function addToFavorites(idRecetteFavori){
		document.getElementById("idRecetteFavori").value = idRecetteFavori;
		document.getElementById("ajouterFavoris").submit();
	}
	
	function submitFilter(){
		document.getElementById("filterRecetteParCategorie").submit();
	}
</script>
</head>
<body>
	<form method="post" action="myRecipes" id="myRecipesForm">
		<input type="hidden" id="idInputr" name="userId">
	</form>
	<form action="ajouterFavoris" id="ajouterFavoris" method="post">
		<input type="hidden" id="idRecetteFavori" name="id">
	</form>
	<form id="recetteDetailForm" method="POST" action="recetteByIdDetail">
		<input type="hidden" name="recetteId" id="idInputn">
	</form>


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

	<div class="content">
		<h2>Explorer les Recettes</h2>

		<!-- Search Bar and Filter Options -->
		<div class="search-and-filter">
			<div class="search-bar">
				<form action="getRecetteByName" method="post">
					<input type="text" name="titre"
						placeholder="Rechercher des recettes...">
					<button type="submit">Rechercher</button>
				</form>
			</div>
			<div class="filter-options">
				<form method="post" action="filterRecetteParCategorie" id="filterRecetteParCategorie">
					<select name="idCategorie">
						<s:iterator value="categories" var="categorie">
							<option onclick="submitFilter()" value="<s:property value="#categorie.id"/>" ><s:property value="#categorie.nom"/></option>
						</s:iterator>
					</select>
					<button>Filtrer</button>
				</form>
			</div>
		</div>

		<!-- Table for displaying recipes -->
		<table>
			<tr>
				<th>Titre</th>
				<th>Catégorie</th>
				<th>Cuisinier</th>
				<th>Action</th>
			</tr>




			<s:iterator value="recettes" var="recette">
				<tr>
					<td><s:property value="#recette.titre" /></td>
					<td><s:property value="#recette.categorie.nom" /></td>
					<td><s:property value="#recette.utilisateur.nom" /></td>
					<td>
						<ul>

							<s:set var="CestUnFavoris" value="%{false}" />

							<s:iterator value="currentUser.favoris" var="favo">
								<s:if test="#recette.id == #favo.id">
									<s:set var="CestUnFavoris" value="%{true}" />
								</s:if>
							</s:iterator>

							<s:if test="#CestUnFavoris">
								<button class="btn-favorites" style="background-color: red;"
									onclick="removeFavoris('<s:property value="#recette.id"/>')">Supprimer
									des favoris</button>
							</s:if>

							<s:else>
								<button class="btn-favorites"
									onclick="addToFavorites('<s:property value="#recette.id"/>')">Ajouter
									aux Favoris</button>
							</s:else>

							<!-- 							<li><a href="">Modifier</a></li> -->
							<!-- 							<li><a href="">Supprimer</a></li> -->
							<li><a href="#"
								onclick="setId(<s:property value="#recette.id"/>)">Détails
									de la recette</a></li>
						</ul>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>

</body>
</html>