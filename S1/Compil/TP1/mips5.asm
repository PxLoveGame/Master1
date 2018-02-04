.data
x:.word 2
y:.word 1

.text

main:
	la $a0, x
	la $a1, y
	jal permute
	
	li $v0,4
	syscall
	j end
	
permute:
	sub $sp, $sp, 4		#reserve un mot (4 octets) sur la pile
	sw $a0, ($sp)		#ecris $a0 dans $sp
	sw $a1, ($a0)		#ecris $a1 dans $a0
	lw $a1, ($sp)		#ecris $sp dans $a1
	jr $ra
	
end: