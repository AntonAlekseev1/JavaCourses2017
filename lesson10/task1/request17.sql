select distinct 'Laptop', model, speed
from laptop
where speed<(select min(speed) from pc)