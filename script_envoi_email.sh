# Contenu de script_envoi_email.sh
echo "Erreur détectée dans le pipeline à la source : $ERREUR_SOURCE" | mail -s "Erreur dans le pipeline" destinataire@example.com
