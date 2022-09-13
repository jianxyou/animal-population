# animal-population

A practice java object oriented programming project simulates the ecosystem of a savanna in course IFT1025 at UDEM. 




# Viellir()
This method ages grass (already implemented) and animals. An animal ages by adding 1 year to
his age and increasing his mass by multiplying it with his growth factor. If an animal exceeds
its maximum age (50 years for a lion and 15 years for an antelope), it dies. An animal is born with age 0
and mass 10. The maturity age of a lion is 5 years while that of an antelope is 2 years. This detail
is important for reproduction, since only mature females can procreate.
# hunt()
This method causes animals to hunt for food. Lions hunt live antelopes and
antelopes eat grass. The first method called in chase() is mix() which
shuffles the order of the animals in the list. In order for everyone's TPs to return the same results, you
must implement mix() like this:

# public void shuffle() {Collections.shuffle(this.individuals, new Random(4));}
Hunting consists of making all the animals of the savannah eat, in the order of the ArrayList.
A lion that hunts must eat antelopes for the equivalent of twice its mass. To do this, it goes through
population antelopes in order of the ArrayList until it can pick up that mass
of food. The hunted antelopes are killed. In each hunting season, only 20% of antelopes
alive at the start of the hunt are “huntable”. Once this number of antelopes have been killed, there is no
more antelope available to lions. If a lion fails to find its mass of food, because
when there are no more antelopes to hunt, he dies.
To survive, an antelope must eat twice its mass of grass each year. This mass is taken from the same
the mass of grass available, which is reduced accordingly. If there is not enough grass to feed an antelope,
she dies.
