a1 = true && true; // t && t retorna true
a2 = true && false; // t && f retorna false
a3 = false && true; // f && t retorna false
a4 = true && false; // t && f retorna false

o1 = true || true;   // t || t retorna true
o2 = true || false;  // t || f retorna true
o3 = false || true;  // f || t retorna true
o4 = false || false; // f || f retorna false

n1 = !true;  // !t retorna false (inverte o verdadeiro)
n2 = !false; // !f retorna true  (inverte o falso)