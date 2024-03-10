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
</style>
<script>
    // Fonction pour afficher le champ d'entrée lors du clic sur "Modifier"
    function afficherChampModif() {
      // Récupérer le div parent
      var divAvisUtilisateur = this.closest('.avisUtilisateur');
      
      var commentaireText = divAvisUtilisateur.querySelector('.commentaire').textContent;
      var supprimerLien = divAvisUtilisateur.querySelector('#supprimerLien');
      var modifierLien = this;

      // Cacher les deux liens
      modifierLien.style.display = 'none';
      supprimerLien.style.display='none';
      divAvisUtilisateur.querySelector('.commentaire').style.display='none';
      
      // Créer un champ d'entrée
      var inputModif = document.createElement('input');
      inputModif.type = 'text';
      inputModif.className = 'inputModif';
      inputModif.value = commentaireText;

      // Ajouter le champ d'entrée au div parent
      divAvisUtilisateur.appendChild(inputModif);
      
      var submitButton = document.createElement('button');
      var cancelButton = document.createElement('button');
      
      submitButton.textContent = "Modifer";
      cancelButton.textContent = "Cancel";
      cancelButton.onclick= reloadFunction();
      
      divAvisUtilisateur.appendChild(submitButton);
      divAvisUtilisateur.appendChild(cancelButton);      

    }
    
    function reloadFunction(){
    	location.reload();
    }
    
    function deleteAvis(idAvisToDelete){
    	document.
    }
    
    function editAvis(idAvisToEdit, rate, commentaire){
    	
    }
  </script>
</head>
<body>

	<form action="modifierAvis" method="POST" id="modifierAvisForm">
		<input type="hidden" name="idAvisToEdit" id="idAvisToEdit">
		<input type="hidden" name="rate" id="rate">
		<input type="hidden" name="commentaire" id="commentaire">
	</form>
	
	<form action="supprimerAction" method="POST" id="supprimerAvisForm">
		<input type="hidden" name="idAvisToEdit" id="idAvisToEdit">
	</form>

	<div class="container">
		<div class="header">
			<h2>Détails de la recette</h2>
		</div>
		<img alt="image de chabbakia" class="recette-img"
			src="data:image/jpeg;base64,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">
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
						<li><s:property value="#ingredient.quantite" />g de <s:property
								value="#ingredient.nom" /></li>
					</s:iterator>
				</ul>
				</p>

			</div>
			<br> <br> <br>
			<div class="avis">
				<h3>Avis</h3>
				<div class="avisUtilisateur">


					<u><strong> ilyas : </strong></u> : 2/5
					<p class="commentaire">" ça m'a l'air delicieux "</p>


				</div>
				<div class="avisUtilisateur">
					<u><strong> Aicha: </strong></u> : 4/5
					<p class="commentaire" id="commentaire">" moi plus que toi"</p>
					<p>
						<a href="#" class="crudAvis" onclick="afficherChampModif.call(this)">Modifier</a> 
						<a href="#"	onclick="" class="crudAvis" id="supprimerLien">Supprimer</a>
					</p>
					<!--  ${avis.utilisateur.nom}-->

					<!--  ${avis.noteSur5}/5-->
				</div>



				<%--                 <c:forEach var="avis" items="${recette.avis}"> --%>
				<%--                 </c:forEach> --%>
			</div>
		</div>
	</div>

</body>
</html>






















-
