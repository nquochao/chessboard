package oliviaproject.chessboard.pgn.convertor;

import java.util.HashMap;

/**
 * @author HaoNguyen Recherche du prérequis, pour distinguer deux pièces (ou
 *         plus) pouvant effectuer le coup :
 * 
 *         Si la première lettre est de a à h 
 *         et la seconde un x ou une lettre
 *         de a à h Prérequis : la pièce doit être dans la
 *         colonne désignée par
 *         cette lettre On peut alors retirer la première
 *         lettre du mot. 
 *         Sinon,
 *         si la première lettre est un chiffre de 1 à 8 
 *         et la seconde un x ou
 *         une lettre de a à h Prérequis : la pièce doit être dans la ligne
 *         désignée par ce chiffre 
 *         On peut alors retirer la première lettre du
 *         mot. 
 *         Sinon, 
 *         si la première lettre est de a à h 
 *         et si la seconde lettre est un chiffre de 1 à 8 
 *         et la troisième un x ou une lettre de
 *         a à h Prérequis : la pièce doit être dans la
 *         case désignée par lq lettre et le chiffre On peut alors retirer la première et la seconde
 *         lettre du mot. 
 *         Sinon, il n'y a pas de prérequis (donc pas
 *         d'ambiguité) Cas d'une attaque, lorsque le déplacement capture une
 *         pièce ennemie :
 * 
 *         Si la première lettre est un x: alors il y a capture de la pièce
 *         ennemie : On peut alors retirer la première lettre du mot. 
 *         Coordonnées du déplacement en
 *         lui-même
 * 
 *         Première lettre : colonne (a,b,c,d,e,f,g,h) 
 *         Seconde lettre (un
 *         chiffre) : ligne (1,2,3,4,5,6,7,8) On peut alors retirer la première
 *         et la seconde lettre du mot. 
 *         
 *         Promotion d'un pion :
 * 
 *         Si la première lettre est un signe =, alors il y a promotion : La
 *         seconde lettre indique la nouvelle pièce, Q, B, N ou R pour Dame,
 *         Fou, Cavalier ou Tour On peut alors retirer la première et la seconde
 *         lettre du mot. Sinon il n'y a pas de promotion. Mise en échec du roi
 *         :
 * 
 *         Si la première lettre est un signe + ou un signe #, alors il y a
 *         échec : On peut alors retirer la première lettre du mot. Sinon il n'y
 *         a pas échec.
 * 
 */
public abstract class Convertor extends HashMap<String, Integer> {

	public abstract void init();

	public abstract Integer convert(String key);
}
