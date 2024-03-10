<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription - Partage de Recettes</title>
    <style>
        /* Replicate the style from index.jsp, modifying as needed */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .registration-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input[type="text"], input[type="email"], input[type="password"] {
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        input[type="submit"] {
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .login-link {
            text-align: center;
            margin-top: 15px;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div class="registration-container">
        <h2>Inscription</h2>
        <form action="inscription" method="post">
            <input type="text" name="nom" placeholder="Nom complet" required>
            <input type="email" name="email" placeholder="Adresse email" required>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required>
            <!-- Add any additional fields as necessary -->
            <input type="submit" value="S'inscrire">
        </form>
        <div class="login-link">
            Déjà inscrit? <a href="index.jsp">Se connecter</a>
        </div>
    </div>
</body>
</html>
