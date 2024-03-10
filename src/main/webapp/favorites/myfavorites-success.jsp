<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste de vos favoris</title>

<s:head />

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 20px;
}

h2 {
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:hover {
	background-color: #f5f5f5;
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
<script type="text/javascript">
	function deleteF(id){
		document.getElementById("idFavoriToDelete").value = id;
		document.getElementById("deleteForm").submit();
		
	}
</script>

<s:if test="isNotDone == 1">
	<script type="text/javascript">
		alert('Désolé, le favoris n\'a pas pu été supprimé !');
	</script>
</s:if>

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


	<h2>Liste de vos favoris</h2>

	<table border="1">
		<thead>
			<tr>
				<th>Titre</th>
				<th>Description</th>
				<th>Temps de Préparation</th>
				<th>Utilisateur</th>
				<th>Catégorie</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="favorites" var="f">
				<tr>
					<td><s:property value="#f.titre" /></td>
					<td><s:property value="#f.description" /></td>
					<td><s:property value="#f.tempsPreparation" /></td>
					<td><s:property value="#f.utilisateur.nom" /></td>
					<td><s:property value="#f.categorie.nom" /></td>
					<td><ul>
							<li><a href="#" onclick="deleteF(<s:property value="#f.id"/>)">Supprimer</a></li>
						</ul>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<form id=deleteForm action="supprimerFavoris" method="post">
		<input type="hidden" id="idFavoriToDelete" name="id">
	</form>

</body>
</html>
