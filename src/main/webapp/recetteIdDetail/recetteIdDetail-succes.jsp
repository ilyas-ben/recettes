<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails de la recette</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f5f5f5;
}

.container {
	max-width: 800px;
	margin: 20px auto;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	overflow: hidden;
}

.header {
	background-color: #3498db;
	color: #fff;
	padding: 20px;
	text-align: center;
}

h2 {
	color: #333;
}

h3 {
	border-bottom: 2px solid #3498db;
	color: #333;
	padding-left: 10px;
}

p {
	color: #555;
	line-height: 1.6;
}

.avis {
	margin-top: 20px;
}

.commentaire {
	margin-top: 10px;
	padding-left: 20px;
	font-style: italic;
}

.avisUtilisateur {
	padding-top: 8px;
	padding-left: 10px;
	border-left: 5px solid #3498db;
	margin: 5px;
}

.avisUtilisateur p {
	/* Propriétés CSS spécifiques pour les balises <p> à l'intérieur de la classe avisUtilisateur */
	color: #333; /* Exemple de propriété */
	font-size: 16px; /* Exemple d'une autre propriété */

	/* Ajoutez d'autres propriétés selon vos besoins */
}

.recette-img {
	display: block;
	/* Pour supprimer l'espace réservé à la ligne de texte */
	margin: 0 auto; /* Pour centrer l'image horizontalement */
	max-width: 100%;
	/* Pour s'assurer que l'image ne dépasse pas la largeur du conteneur */
	height: auto;
	/* Pour maintenir le rapport hauteur/largeur de l'image */
	border-radius: 8px;
	margin-bottom: 10px;
	margin-top: 40px;
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
<script>
	// Fonction pour afficher le champ d'entrée lors du clic sur "Modifier"
	function afficherChampModif(idToModify) {
		// Récupérer le div parent
		var modifierLien = document.getElementById('modifierLien');
		var divAvisUtilisateur = modifierLien.closest('.avisUtilisateur');
		var supprimerLien = divAvisUtilisateur.querySelector('#supprimerLien');
		var ancienCommentaire = divAvisUtilisateur.querySelector('.commentaire').textContent;
		var ancienRate = divAvisUtilisateur.querySelector('#ancienRate').textContent;
		
		

		console.log("Bonjour, la valeur du commntiare  : " +ancienCommentaire);

		// Cacher les deux liens
		modifierLien.style.display = 'none';
		supprimerLien.style.display = 'none';
		divAvisUtilisateur.querySelector('.commentaire').style.display = 'none';

		// creation du formulaire de modofication, et l ajouter dans le div
		var formModifierAvis = document.createElement('form');
		formModifierAvis.action = 'modifierAvis';
		formModifierAvis.id = 'formModifierAvis';
		formModifierAvis.method = 'post';

		// ajout du form au div parent
		divAvisUtilisateur.appendChild(formModifierAvis);

		var label = document.createElement('label');

		// Configuration de l'attribut 'for' pour lier le label à l'input de la note
		label.setAttribute('for', 'rate');
		label.textContent = 'Note sur 5';
		formModifierAvis.appendChild(label);

		// inputer le nombre de note /5

		var selectElement = document.createElement('select');
		selectElement.name = 'rate';
		selectElement.id = 'rate';
		
		// configuration du select, lancienne note sera selectionnée initialement.     
		for (var i = 1; i <= 5; i++) {
			var optionElement = document.createElement('option');
			optionElement.value = i;
			optionElement.text = i;
			
			if(optionElement.value == ancienRate)   optionElement.selected = true;
			
			selectElement.appendChild(optionElement);
		}
		
		//selectElement.value = 
		
		// ajouter la liste déroulante au form
		formModifierAvis.appendChild(selectElement);

		// Créer un champ d'entrée pr le commentaire
		var nouvCommentaire = document.createElement('input');
		nouvCommentaire.type = 'text';
		nouvCommentaire.name = 'commentaire';
		nouvCommentaire.placeholder = 'Votre nouveau commentaire';
		nouvCommentaire.value = ancienCommentaire;
		formModifierAvis.appendChild(nouvCommentaire); // et l ajouter dans le form

		// Ajout des buttons sumouettre et cancel au form : 
		var submitButton = document.createElement('button');
		var cancelButton = document.createElement('button');

		submitButton.textContent = "Modifer";
		cancelButton.textContent = "Cancel";

		formModifierAvis.appendChild(submitButton);
		formModifierAvis.appendChild(cancelButton);
		
		// Creation et configuration de l input qui contiendra l id de l avis à modifer
		var idInput = document.createElement('input');
		idInput.name= 'idAvis';
		idInput.value= idToModify;
		idInput.setAttribute('type', 'hidden');
		
		// ajout au form
		formModifierAvis.appendChild(idInput);
		
		
		// soumission du formulaire
		submitButton.onclick= function(formModifierAvis){
			formModifierAvis.submit();
		}

		cancelButton.href = '#';
		cancelButton.onclick = function() {
			document.location.reload(true);
		};

	}

	     function deleteAvis(idAvisToDelete){
	    	if(confirm('Voulez-vous vraiment supprimer votre avis de cette recette')){
	    		document.getElementById("idAvisToDelete").value = idAvisToDelete ;
		     	document.getElementById("supprimerAvisForm").submit();
	    	} 
	     }

	//     function editAvis(idAvisToEdit, rate, commentaire){

	//     }
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
	<form action="modifierAvis" method="POST" id="modifierAvisForm">
		<input type="hidden" name="idAvis" id="idAvisToEdit"> <input
			type="hidden" name="rate" id="rate"> <input type="hidden"
			name="commentaire" id="commentaire">
	</form>

	<form action="supprimerAvis" method="POST" id="supprimerAvisForm">
		<input type="hidden" name="idAvis" id="idAvisToDelete">
	</form>

	<div class="container">
		<div class="header">
			<h2>Détails de la recette</h2>
		</div>
		<img alt="<s:property value="recette.titre"/>" class="recette-img"
			src="data:image/jpeg;base64,${recipImage}">
		<div class="content">
			<div style="padding-left: 20px">
				<p>
					<strong>Titre:</strong> ${recette.titre}
				</p>
				<p>
					<strong>Description:</strong> ${recette.description}
				</p>
				<p>
					<strong>Temps de préparation:</strong> ${recette.tempsPreparation}
					minutes
				</p>

				<p>
					<strong>Catégorie:</strong> ${recette.categorie.nom}
				</p>
				<p>
					<strong>Ingrédients</strong> :
				</p>
				<p>
				<ul>
					<s:iterator value="recette.ingredients" var="ingredient">
						<li><s:property value="#ingredient.quantite" /> de <s:property
								value="#ingredient.nom" /></li>
					</s:iterator>
				</ul>
				</p>

			</div>
			<br> <br> <br>
			<div class="avis">
				<h3>Avis</h3>

				<s:iterator value="recette.avis" var="av">
					<div class="avisUtilisateur" id="<s:property value="#av.id"/>">
						<u><strong><s:property value="#av.utilisateur.nom" /></strong></u>

						<!-- RENDRE  P INBLOKC OU INLINE? IMPLEMENTEZ ADD AVIS -->

						:
						<p id="ancienRate" style="display: inline;">
							<s:property value="#av.noteSur5" />
						</p>
						/5
						<s:if test="#session.user.id == #av.utilisateur.id">
							<p style="display: inline;">
								<i style="">(Vous)</i>
							</p>
						</s:if>
						<p class="commentaire"><s:property value="#av.commentaire" /></p>
						<p>
							<s:if test="#session.user.id == #av.utilisateur.id">
								<a href="#<s:property value="#av.id"/>" class="crudAvis"
									id="modifierLien"
									onclick="afficherChampModif(<s:property value="#av.id"/>)">Modifier</a>
								<a href="#" onclick="deleteAvis(<s:property value="#av.id"/>)"
									class="crudAvis" id="supprimerLien">Supprimer</a>
							</s:if>
						</p>




						<!-- if session user .avis = null -->

					</div>
					<s:set var="itsMyAvis" value="%{false}" />
				</s:iterator>
				<s:iterator value="recette.avis" var="av">
					<s:if test="#session.user.id == #av.utilisateur.id">
						<s:set var="itsMyAvis" value="%{true}" />
					</s:if>
				</s:iterator>
				<s:if test="!#itsMyAvis">
					<div class="addAvisForm">
						<form action="ajouterAvis" method="post">
							Ajouter votre avis : Note sur 5 : <select name="rate">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>

							</select> <input type="text" name="commentaire"
								placeholder="Ajouter votre propre commentaire ici"> <input
								type="hidden" name="idRecette"
								value="<s:property value="recette.id"/>">
							<button>Publier</button>
						</form>
					</div>
				</s:if>

			</div>
		</div>
	</div>

</body>
</html>