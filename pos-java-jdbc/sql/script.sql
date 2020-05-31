select fone.usuariopessoa, userp.nome, fone.numero, fone.tipo, userp.email 
from telefoneuser as fone
inner join 
userposjava as userp on fone.usuariopessoa = userp.id

select * from userposjava
select * from telefoneuser
