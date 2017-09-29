#Affiche la valeur absolu d'un entier saisi

.text
main:	li $v0,  5		#syscall demandera la saisi d'un entier vers $v0
	syscall			
	move $v1, $v0		#copie de $v0 dans $v1
	bgez $v1, pos		#si $v0 négatif on saute à pos. 
	neg $v1, $v0		#Si $v0 negatif, donne la valeur absolu positive.
	li $v0, 1		#définie $v0 en écriture (pour un int) sur la sortie standard
	la $a0, ($v1)
	syscall
	j end

pos:	li $v0, 1		
	la $a0, ($v1)
	syscall

end: