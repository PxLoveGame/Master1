(defun mymember (x l &key (test #'eql)) ; pour changer le comportement du test => #'eql par exemple ou d'autres qu'on a pu définir.
  (if(NULL l)
    ()
    (if (apply test (list  x (car l)))
      l
      (mymember x (cdr l) :test test)
    )
  )
)
;apply et funcall (important)
;=== Arité variables ===> une liste d'arguments qui n'ont pas de noms (le nombres d'arguments peut varié, ils sont traité jusqu'au dernier) $rest

;=== fonction destructive === (Partie 3.3 dans le cours)

(setf l1 '(1 2 3))
(setf l2 '(4 5 6))

(defun myappenddest (l1 l2) ;concatene 2 listes
  (if NULL (car l1)
    (setf (cdr l1) l2) ; remplacer cons par progn
    (myappenddest (cdr l1) l2)
  )
)



; === calculette ===

(defun calcul (e)
  (if(atom e)
    e
    (if(eql 3 (lenght e))
      (apply (car e) (list (nth 1 e) (nth 2 e)))
      ()
    )
  )
)
