BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS recipes_users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS categories_recipes;
DROP TABLE IF EXISTS meal_plans;
DROP TABLE IF EXISTS meal_plan_users;
DROP TABLE IF EXISTS meal_plan_sections;
DROP TABLE IF EXISTS sections;
DROP TABLE IF EXISTS section_recipes;
DROP TABLE IF EXISTS meal_plan_recipes;
DROP TABLE IF EXISTS sections_recipes; -- in case someone 
DROP USER  IF EXISTS final_capstone_owner;
DROP USER  IF EXISTS final_capstone_appuser;

CREATE TABLE recipes (
	recipe_id SERIAL,
	recipe_name varchar(50) NOT NULL UNIQUE,
	recipe_img varchar,
	description varchar,
	ingredients varchar,
	time integer,
	servings integer,
	instructions varchar,
	categories varchar
);

CREATE TABLE categories (
	category_id SERIAL,
	category varchar
);

CREATE TABLE meal_plans (
	meal_plan_id SERIAL,
	meal_plan_title varchar(50) NOT NULL,
	meal_plan_description varchar
);

CREATE TABLE meal_plan_sections (
	meal_plan_id_fk integer,
	section_id_fk integer
);

CREATE TABLE meal_plan_users (
	meal_plan_id integer,
	user_id integer,
	PRIMARY KEY (meal_plan_id, user_id)
);

CREATE TABLE categories_recipes (
	category_id integer,
	recipe_id integer
);

CREATE TABLE sections_recipes (
	section_id_fk integer,
	recipe_id_fk integer
);

CREATE TABLE sections (
	section_id SERIAL,
	section_title varchar
);

CREATE TABLE recipes_users (
	recipe_id integer,
	user_id integer,
	PRIMARY KEY (recipe_id, user_id)
);

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

-- All passwords are: password

-- users table
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('buddy','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('frank','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- meal plans table
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (1, 'A Meal Plan', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (2, 'Another Meal Plan', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (3, 'I am hungry', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (4, '3 Meals a Day', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (5, 'Good Bytes', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (6, 'My Bytes', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (7, 'All Bark No Byte', 'This is a meal plan created to demo for Team Good Bytes.');
INSERT INTO meal_plans (meal_plan_id, meal_plan_title, meal_plan_description) VALUES  (8, 'I am hungry', 'This is a meal plan created to demo for Team Good Bytes.');


-- meal plan users table
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (1,2);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (2,1);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (2,2);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (1,1);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (3,1);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (1,3);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (5,3);
INSERT INTO meal_plan_users (meal_plan_id, user_id)VALUES (1,4);

-- recipes table
INSERT INTO recipes (recipe_name, recipe_img, description, ingredients, time, servings, instructions, categories) VALUES ('Classic Italian Meatballs', 'https://www.thereciperebel.com/wp-content/uploads/2021/02/meatball-recipe-www.thereciperebel.com-600-20-of-21.jpg', 'This Classic Meatballs recipe is an absolute favorite, and for great reason! Bake to perfection, and these classic Italian meatballs could be the answer to tonights dinner jam!',																														  '½ cup (54 g) fresh breadcrumbs, from 1-2 slices white bread (crust removed), ¼ cup (60 ml) milk, 2 egg yolks, ½ cup (50 g) grated pecorino Romano cheese, 2 garlic cloves, finely chopped or grated,1 tablespoon (15 g) kosher salt or 1 ½ teaspoons table salt
,1 teaspoon ground black pepper
,1 pound (450 g) ground beef chuck
,1 pound (450 g) ground pork, or veal
,¼ cup grated onion, optional
,⅓ cup (30 g) chopped parsley, or basil
, olive oil', 30, 2, 
'Put the breadcrumbs in a small bowl and pour the milk over. Let them soak for 5 minutes. Add the egg yolks, cheese, garlic, salt, and pepper to the bowl and mash together to form a coarse paste.

* Put the beef, pork and onion (if using) in a large bowl and mix with a fork to blend. Add the bread mixture and parsley. Blend everything together well, using your hands, large wooden spoon or large fork. You can also mix in a standing mixer on low speed, just until blended. The meatball mixture can be mixed and refrigerated 2 days ahead of shaping and cooking.

* FORM THE MEATBALLS: Use a ¼-cup measuring cup or cookie scoop to portion the mixture and roll lightly into balls. They don’t have to be perfect — craggy meatballs have character and hold the sauce better!
* TO COOK ON THE STOVETOP:
Pour enough oil into a large skillet to coat the bottom and place over medium-high heat. When the pan is hot, add as many meatballs as will fit in the pan without crowding. Note: They should sizzle as soon as they hit they pan or the pan isn’t hot enough.
Brown the meatballs on all sides. Cover the pan, lower the heat and continue cooking and until the meatballs are firm and cooked through (165 degrees on a instant thermometer), which should take about 15 minutes total. Repeat the frying with remaining meatballs, pouring out the oil and adding a fresh layer.
* TO FINISH COOKING IN TOMATO SAUCE:
After browning the meatballs, transfer them from the skillet to a simmering pot of marinara sauce. Cook 10-12 minutes.
* TO BAKE THE MEATBALLS:
Preheat the oven to 425 degrees.
Arrange the meatballs on an olive-oiled coated rimmed baking sheet. Bake 10 minutes, then turn the meatballs over with a spatula or pair of tongs. Continue baking until nicely browned and cooked through, another 10-12 minutes.
Serve the warm meatballs with marinara sauce or your favorite prepared tomato sauce.', 'Italian, Difficulty: Easy, Dinner');

INSERT INTO recipes (recipe_name, recipe_img, description, ingredients, time, servings, instructions, categories) VALUES ('Greek Salad', 'https://cdn.loveandlemons.com/wp-content/uploads/2019/07/greek-salad-recipe-846x846.jpg', 'This easy Greek salad recipe is a flavorful, refreshing summer side dish! If you make it ahead for a gathering, save a few mint leaves to add right before serving.',																														  '2 small heads of soft lettuce, butter lettuce or similar, Lemon Vinaigrette, 1 Persian cucumber (thinly sliced)
,¼ cup shaved Parmesan cheese
,2 tablespoons pepitas
,1 avocado, thinly sliced
,¼ cup microgreens
,Flaky sea salt (optional), ½ cup raw almonds, ½ tablespoon tamari', 15, 1, 'Roast the almonds: Preheat the oven to 350°F and line a baking sheet with parchment paper. Place the almonds on the sheet and toss with tamari. Bake for 10 to 14 minutes or until browned. Remove from the oven and let cool for 5 minutes.
* Assemble the salad. In a large bowl toss the lettuce with a few spoonfuls of the dressing. Add the cucumber, parmesan, pepitas, avocado, and tamari almonds. Drizzle with more dressing and top with microgreens. Season to taste with flaky sea salt, if desired.', 'Side Dish, Difficulty: Easy');
																														  
INSERT INTO recipes (recipe_name, recipe_img, description, ingredients, time, servings, instructions, categories) VALUES ('Perfect Roast Chicken', 'https://food.fnr.sndimg.com/content/dam/images/food/fullset/2011/4/12/0/FN_Pats-Beercan-Chicken_s4x3.jpg.rend.hgtvcom.616.462.suffix/1382539876282.jpeg','This roast chicken recipe is a classic for a reason: It’s simple, succulent, comforting, and versatile, with golden-brown crackling skin and juicy, tender meat.','
1 5- to 6-pound roasting chicken,
Kosher salt,
Freshly ground black pepper,
1 large bunch fresh thyme,
1 lemon (halved),
1 head garlic (cut in half crosswise),
2 tablespoons butter (melted),
1 Spanish onion, thickly sliced, 
1 cup chicken stock, preferably homemade, 
2 tablespoons all-purpose flour', 120, 4, 'Preheat the oven to 425 degrees.
* Remove the chicken giblets. Rinse the chicken inside and out. Remove any excess fat and leftover pinfeathers and pat the outside dry. Place the chicken in a large roasting pan. Liberally salt and pepper the inside of the chicken. Stuff the cavity with the bunch of thyme, both halves of the lemon, and all the garlic. Brush the outside of the chicken with the butter and sprinkle again with salt and pepper. Tie the legs together with kitchen string and tuck the wing tips under the body of the chicken. Scatter the onion slices around the chicken.
* Roast the chicken for 1-1/2 hours, or until the juices run clear when you cut between a leg and thigh. Remove to a platter and cover with aluminum foil while you prepare the gravy.
* Remove all the fat from the bottom of the pan, reserving 2 tablespoons in a small cup. Add the chicken stock to the pan and cook on high heat for about 5 minutes, until reduced, scraping the bottom of the pan. Combine the 2 tablespoons of chicken fat with the flour and add to the pan. Boil for a few minutes to cook the flour. Strain the gravy into a small saucepan and season it to taste. Keep it warm over a very low flame while you carve the chicken.
* Slice the chicken onto a platter and serve immediately with the warm gravy.', 'American, Difficulty: Medium, Dinner');

INSERT INTO recipes (recipe_name, recipe_img, description, ingredients, time, servings, instructions, categories) 
VALUES 

('Pork Fried Rice', 'https://www.lecremedelacrumb.com/wp-content/uploads/2021/01/pork-fried-rice-2sm-3.jpg', 'This restaurant-style Chinese Pork Fried Rice recipe is the best ever! It tastes even better than takeout and is ready to eat in just 15 minutes! Keep reading to learn how to make homemade fried rice with this easy, classic recipe.', 
		'1 pound ground pork, 4 cups cooked white rice, ¼ cup sesame oil, ½ white onion - diced, ⅓ cup soy sauce, ½ teaspoon garlic powder, ¼ teaspoon ground ginger, ¼-½ teaspoon crushed red pepper flakes, 1 cup frozen peas and carrots, 6 green onions - chopped, 3 eggs - whisked', 15, 5, 
 'In a large skillet over medium-high heat, cook the ground pork until browned all over being sure to break up into small pieces with a wooden spoon or a spatula as it cooks. Transfer to a dish and cover to keep warm.
* In the same skillet, add oil and onions and saute for 1-2 minutes til tender.
* Stir in rice, soy sauce, garlic powder, ginger, and red pepper flakes. Cook, stirring occasionally, for 5-8 minutes until rice is browned.
* Stir in carrots and peas and pineapple for 2-3 minutes til heated through.
* Gently push the contents of the pan to one side to make room to cook the eggs. Add whisked eggs to empty portion of the pan and stir for 2-3 minutes til cooked. Stir into the rice.
* Stir in green onions and cooked pork and serve.', 'Asian, Chinese, Difficulty: Easy, Dinner, Lunch'),
	   
	   ('Carne Asada Tacos', 'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2018/04/Carne-Asada-Tacos-3.jpg', 'Carne asada tacos are delicious flank steak, Mexican-inspired street tacos! They’re perfect for outdoor grilling, parties or even your next Taco Tuesday.', 
		'1 recipe carne asada, 2 avocados, 3 tbsp cotija cheese, 1/3 cup onion, finely diced, 1/2 cup fresh cilantro, chopped, 6 tortillas, limes for garnish', 120, 2, 
		'MAKE THE CARNE ASADA: Marinate and cook the steak according to recipe instructions.
* PREPARE THE TOPPINGS: While the steak cooks, go ahead and prepare the guacamole and whatever other toppings you would like.  (If you are making homemade corn tortillas, you can also make those while the steak is marinating.)
* ASSEMBLE THE TACOS: Once the carne asada is cooked and has rested for at least 10 minutes, slice or dice the steak into bite-sized pieces.  Fill each corn tortilla with your desired amount of steak and toppings.
Serve. Then serve up the tacos immediately and enjoy!', 
		'Mexican, Difficulty: Easy, Lunch, Dinner'),
	  
	  ('Butternut Squash Soup', 'https://cdn.loveandlemons.com/wp-content/uploads/2019/10/butternut-squash-soup-recipe-846x846.jpg', ' On a brisk day, there’s nothing more soothing than chopping and simmering vegetables over a warm stove. As you cook, the kitchen fills with the cozy scent of sage, rosemary, and butternut squash, and when you’re finished, steaming bowls of butternut squash soup (that will last all week!) reward you for your work.We’ve been loving this soup lately – it’s creamy and nourishing, perfect for satisfying cozy fall cravings. With only 10 ingredients, it’s easy to make, so cook a big batch today, and enjoy the soup all week long! Happy fall.', 
	   '2 tablespoons extra-virgin olive oil,
1 large yellow onion (chopped),
½ teaspoon sea salt,
1 (3-pound) butternut squash,
3 garlic cloves (chopped),
1 tablespoon chopped fresh sage,
½ tablespoon minced fresh rosemary,
1 teaspoon grated fresh ginger,
3 to 4 cups vegetable broth,
Freshly ground black pepper', 45, 4, 'Heat the oil in a large pot over medium heat. Add the onion, salt, and several grinds of fresh pepper and sauté until soft, 5 to 8 minutes. Add the squash and cook until it begins to soften, stirring occasionally, for 8 to 10 minutes.
* Add the garlic, sage, rosemary, and ginger. Stir and cook 30 seconds to 1 minute, until fragrant, then add 3 cups of the broth. Bring to a boil, cover, and reduce heat to a simmer. Cook until the squash is tender, 20 to 30 minutes.
* Let cool slightly and pour the soup into a blender, working in batches if necessary, and blend until smooth. If your soup is too thick, add up to 1 cup more broth and blend. Season to taste and serve with parsley, pepitas, and crusty bread.', 'American, Difficulty: Easy, Side Dish'),
	   
	   ('Chicken Tikka Masala', 'https://cafedelites.com/wp-content/uploads/2018/04/Best-Chicken-Tikka-Masala-IMAGE-1.jpg', 'This rich and creamy flavoursome Chicken tikka rivals any Indian restaurant! Why go out when you can make it better at home! With aromatic golden chicken pieces swimming in an incredible curry sauce, this Chicken Tikka Masala recipe is one of the best you will try!', 
		'For the chicken marinade:
28 oz (800g) boneless and skinless chicken thighs cut into bite-sized pieces,
1 cup plain yogurt,
1 1/2 tablespoons minced garlic,
1 tablespoon ginger,
2 teaspoons garam masala,
1 teaspoon turmeric,
1 teaspoon ground cumin,
1 teaspoon Kashmiri chili (or 1/2 teaspoon ground red chili powder),
1 teaspoon of salt,
For the sauce:
2 tablespoons of vegetable/canola oil,
2 tablespoons butter,
2 small onions (or 1 large onion) finely diced,
1 1/2 tablespoons garlic finely grated,
1 tablespoon ginger finely grated,
1 1/2 teaspoons garam masala,
1 1/2 teaspoons ground cumin,
1 teaspoon turmeric powder,
1 teaspoon ground coriander,
14 oz (400g) tomato puree (tomato sauce/Passata),
1 teaspoon Kashmiri chili (optional for colour and flavour),
1 teaspoon ground red chili powder (adjust to your taste preference),
1 teaspoon salt,
1 1/4 cups of heavy or thickened cream (use evaporated milk for lower calories),
1 teaspoon brown sugar,
1/4 cup water if needed,
4 tablespoons Fresh cilantro or coriander to garnish', 60, 5, 
		'In a bowl, combine chicken with all of the ingredients for the chicken marinade; let marinate for 10 minutes to an hour (or overnight if time allows).
* Heat oil in a large skillet or pot over medium-high heat. When sizzling, add chicken pieces in batches of two or three, making sure not to crowd the pan. Fry until browned for only 3 minutes on each side. Set aside and keep warm. (You will finish cooking the chicken in the sauce.)
* Melt the butter in the same pan. Fry the onions until soft (about 3 minutes) while scraping up any browned bits stuck on the bottom of the pan. 
* Add garlic and ginger and sauté for 1 minute until fragrant, then add garam masala, cumin, turmeric and coriander. Fry for about 20 seconds until fragrant, while stirring occasionally.
* Pour in the tomato puree, chili powders and salt. Let simmer for about 10-15 minutes, stirring occasionally until sauce thickens and becomes a deep brown red colour.
* Stir the cream and sugar through the sauce. Add the chicken and its juices back into the pan and cook for an additional 8-10 minutes until chicken is cooked through and the sauce is thick and bubbling. Pour in the water to thin out the sauce, if needed.
* Garnish with cilantro (coriander) and serve with hot garlic butter rice and fresh homemade Naan bread!', 'Asian, Indian, Difficulty: Hard, Dinner'),
	   
	   ('Stovetop Burgers', 'https://www.thecookierookie.com/wp-content/uploads/2023/04/featured-stovetop-burgers-recipe-500x500.jpg', 'Stovetop Burgers are easier than you think! Learning how to cook the perfect burgers on the stove means you can make this easy dinner anytime of year. Form simply-seasoned burger patties, pan-fry them in a skillet, and find the exact cooking time and temperature to make sure you get it right. You’ll be cooking the best hamburgers and cheeseburgers, with your toppings of choice, each and every time!', 
		'1 pound ground chuck 80%-85% lean, kosher or sea salt to taste, freshly ground black pepper to taste, 4 slices American or Cheddar Cheese, 4 Burger Buns', 20, 4, 
		'Place ground chuck in a medium-size mixing bowl and sprinkle with salt & pepper. Mix gently to combine, but don’t overwork the meat. 1 pound ground chuck,kosher or sea salt to taste,freshly ground black pepper to taste ground chuck in a mixing bowl
* Divide the meat into four equal portions.
* Place one portion on a plate and gently press it into a 1-inch thick, round patty. (The patty should not be smooth – leave some craggy edges and don’t press it too flat.) four burger patties on a plate
* With your thumb or fingers, press a “moat”, approximately ¼-½ inch from the edge, around the circumference of the burger – be sure the edge of the burger is a little higher than the indentation. This will ensure the burger will flatten out while cooking. Repeat this process with each portion.
raw burger patty with a "moat" pressed into it.
* Heat a large skillet over medium-high heat. When the pan is hot, carefully place the patties in the skillet (no need to grease skillet) leaving some space between each patty.
* Cook the burgers until nicely seared and they have browned halfway up the sides.
* Flip the burgers and cook to your liking. (*NOTE – To ensure all bacteria has been killed, the FDA recommends cooking burgers to an internal temperature of 160°F.)
* If adding cheese: remove the pan from the heat and place the cheese atop the burgers. Cover the skillet and allow the residual heat/steam to melt the cheese. 4 slices American or Cheddar Cheese
close up in a cheeseburger in a skillet.
* Serve on toasted buns topped with your favorite toppings.', 'American, Difficulty: Easy, Lunch, Dinner'),
	   
	   ('Sonoran Hot Dog', 'https://www.muydelish.com/wp-content/uploads/2021/06/Authentic-Sonoran-Hot-Dogs.jpg', 'Sonoran Hot Dogs are taken to a higher level! With a soft bun, hot dog wrapped in bacon and topped with sautéd onions, chopped tomatoes plus your favorite condiments. Once you have one of these, you’ll never go back to standard hot dogs!', 
		'4 large hot dog buns, 4 large all-beef hot dogs, 4 slices thin bacon, ½ large white or yellow onion sliced thinly, 1 roma tomato chopped into small cubes, Mayonnaise, Mustard, Ketchup, Hot sauce to taste, Chiles Toreados', 25, 4, 
		'Start placing the edge of the bacon on the top of the hot dog and wrap it by overlapping the bacon while going down. This will keep the bacon from opening while you cook it.
* At the bottom of the hot dog, using your finger, tuck in the end of the bacon inside of the bacon itself to secure that edge. If this fails, then you can secure it with a toothpick until you get better at this.
* Cook the onions and hot dogs in a large skillet. Keep rotating the hot dogs until all sides are evenly crispy.
* Right before assembling the hot dog, steam the bun by loosely wrapping them in paper towels (or large ziplock bag) and microwave them for about 7 to 10 seconds. Just enough to get them soft & warm.
* Place the hot dog in the bun.
* Add about 1 tablespoon of cooked or raw onions.
* Add about 2 tablespoons of chopped tomato.
* Spread some mayonnaise a over the tomatoes and press lightly to blend the tomatoes with the mayo. Add mustard, ketchup & hot sauce if you like.', 'Mexican, Difficulty: Easy, Lunch'),
	   
	   ('Lasagne Alla Bolognese', 'https://www.sugarsaltmagic.com/wp-content/uploads/2022/11/Lasagna-Alla-Bolognese-8FEAT-500x500.jpg', 'This here is the authentic Italian Lasagna Bolognese recipe (Lasagne alla Bolognese), the one you would eat in Italy in the best Italian restaurants. It’s the authentic recipe born in the city of Bologna, WITHOUT MOZZARELLA. Oh yes, because, contrary to popular belief, the authentic classic recipe of Lasagna Bolognese does NOT want mozzarella.', 
		'300 g (10 oz) of coarsely ground beef,
150 g (5 oz) of sliced pancetta (you can replace pancetta with minced pork),
300 g (1 1⁄4 cup) of tomato passata or crashed peeled tomato,
1 small carrot (about 50 g),
1 celery stalk (about 50 g),
1 small onion (about 50 g),
100 ml (1⁄2 cup) of dry white wine,
100 ml (1⁄2 cup) of whole milk,
300 ml (1 1⁄2 cup) of meat broth,
3 tablespoons of extra virgin olive oil,
fine salt,
freshly ground black pepper', 260, 5, 
		'Preheat the oven to 375 degrees F.
* Butter the lasagne pan well and add a very thin layer of meat sauce.
* Add the first layer of lasagne noodles and in order, cover with meat
* sauce, about 3 ladles besciamella, and a generous sprinkle of
* Parmesan cheese. Repeat the same process until you reach the top of the dish. Make sure the lasagne noodles are
soaking into the sauce.
* When done layering the ingredients, top the lasagne with a final ladle of meat sauce and some besciamella, add
a few thin slices of butter and finish with some grated Parmesan.
* Bake for about 30 minutes.
*  Heat a broiler. When your fantastic lasagne alla bolognese is cooked, give it a nice crisp top by broiling it for
about 5 minutes.
* Always serve this dish with extra-virgin olive oil and some grated parmesan, to taste', 'Italian, Dinner, Difficulty: Medium'),
	   
	   ('Fiorentina Steak', 'https://img.veenaworld.com/wp-content/uploads/2021/09/Fiorentina-Steak-1024x683.jpg?imwidth=1080', 'If you ask any Italian, living in the country or abroad, the best Italian food according to them would be the Fiorentina Steak. Traditionally known as Bistecca Fiorentina, this famous Italian dish is a soft spot for many locals. A T-bone beef steak is cooked for five to seven minutes so that the outside layer is grilled, leaving the inner steak rare or medium-rare.', 
		'1 T-bone or porterhouse steak (at least 3 inches thick & 3-3½ pounds), 1 bunch fresh rosemary, 1 bunch fresh sage, 2 tablespoons extra virgin olive oil, Kosher salt & freshly ground black pepper to taste', 100, 4, 
		'Preheat a grill pan on medium-high heat. Pat the steak dry, and season both sides generously with salt and pepper.
* Tie the rosemary and sage bunches together with butcher’s twine to form an herb brush. Use the herbs to brush the steak with olive oil.
* Place the steak on the grill pan, and char it well: cook about 12 minutes on the first side, flip, and cook about 9 minutes on the second side. Like we said earlier, this steak is traditionally served rare.
* When the steak is done, remove it from the grill pan and allow it to stand for 5 minutes so that the juices are retained when the meat is cut. Carve off the fillet and strip steaks, and slice before serving. Serve hot, and enjoy!','Easy'),
	  
	  ('Banh Xeo', 'https://vietnamnomad.com/wp-content/uploads/2022/07/Banh-Xeo-A-guide-to-Vietnamese-Pancakes-Vietnamnomad.jpg', 'Vietnamese bánh xèo, often called Vietnamese pancakes, or Vietnamese crêpes, are great for family style dinners and you can prep the batter a night ahead of time.', 
		'255 g (1 3/4 c) rice flour, 85 g all-purpose flour, 2-3 tsp turmeric, 28 fl oz (3.5 c) water, 14 fl oz (396.9 ml) coconut cream if unavailable use coconut milk, 1 tsp salt, 1 spring green onions chopped, 
		1 lb (453 g) shrimp (heads removed and deveined size 45/50 or 60/70), 1.5 lb (680.39 g) pork belly, 1 medium yellow onion thinly sliced, 1.5 lb (680.39 g) bean sprouts, 1/2 c dry mung beans, 1 head mustard greens caỉ xanh, 1 bunch mint, 1 bunch cilantro, 1 bunch Vietnamese perilla, dipping sauce', 
		240, 5, 
		'PREPARE BATTER:
Combine all batter ingredients except scallions in a large bowl for at least 3 hours, or overnight. Add scallions only right before making the crêpes.
* PREPARE FILLINGS:
Steam or soak mung beans in water until soft.
mung beans soaking in water
Boil pork until cooked through and soft, then slice thinly.
boiling pork belly in small pot
Remove shrimp heads and devein shrimp if desired.
Wash bean sprouts and veggies.
* MAKING BÁNH XÈO – EACH CRÊPE TAKES ABOUT 8-10 MINUTES:
On medium-high heat add 1-2 teaspoons of oil and some onions.
Immediately add a few pieces of pork and shrimp. Sauté, lightly mixing until very lightly browned and fragrant.
Pour in some batter and quickly tilt & rotate the pan so the batter is evenly spread. Add more batter if it was not enough to cover the pan. There should only be a thin layer of batter that almost flakes off at the pan edges where it is thinner. If your batter does not do that and is too thick, add a few tbsp water to the batter and mix to thin it out.
poured batter to the pan.
Lower the heat to medium. Add some mung beans, bean sprouts, and cover with a lid for about 3 minutes, or until bean sprouts are slightly cooked. The batter should also be slightly cooked and transparent around the edges. This step cooks the top side of the ingredients and batter while it steams since we will not be flipping the crepe.
added bean sprouts and mung bean to pan.Remove the lid, lower heat to medium-low and wait for the crêpe to become crisp. This takes about 5-7 minutes. This step lets the ingredients fully cook through, including the batter. It also lets steam escape so the batter can crisp up. Brush on a little oil around the edges if you are not seeing or hearing enough batter to pan contact. Fold in half, transfer to a plate and serve immediately. For batter troubleshooting please see the troubleshooting section in the post above.', 'Asian, Vietnamese, Difficulty: Hard, Lunch');


-- recipe & users joined table 
insert into recipes_users (recipe_id, user_id) VALUES (1, 1);
insert into recipes_users (recipe_id, user_id) VALUES (2, 1);
insert into recipes_users (recipe_id, user_id) VALUES (3, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (4, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (5, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (6, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (7, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (8, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (9, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (10, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (11, 1);
INSERT INTO recipes_users (recipe_id, user_id) VALUES (12, 1);


-- categories table
INSERT INTO categories (category) VALUES ('Chinese');
INSERT INTO categories (category) VALUES ('American');
INSERT INTO categories (category) VALUES ('Easy');
INSERT INTO categories (category) VALUES ('Hard');
INSERT INTO categories (category) VALUES ('Mexican');
INSERT INTO categories (category) VALUES ('Indian');
INSERT INTO categories (category) VALUES ('Vietnamese');


-- categories & recipes joined table
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (2,1);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (2,2);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,1);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,2);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (1,4);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,4);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (5,5);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,5);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (2,6);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,6);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,7);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (6,7);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (2,8);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,8);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (2,9);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,9);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,10);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (3,11);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (7,12);
INSERT INTO categories_recipes (category_id, recipe_id) VALUES (4,12);

-- sections table
INSERT INTO sections (section_title) VALUES ('Breakfast');
INSERT INTO sections (section_title) VALUES ('Lunch');
INSERT INTO sections (section_title) VALUES ('Dinner');
INSERT INTO sections (section_title) VALUES ('Healthy');
INSERT INTO sections (section_title) VALUES ('Tasty');


-- sections_recipes table
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (1,1);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (1,2);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (1,3);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (2,5);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (3,4);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (4,2);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (2,1);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (3,11);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (3,8);
INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (4,6);

-- meal_plan_sections table
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (1, 1);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (1, 2);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (1, 3);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (1, 4);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (1, 5);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (2, 2);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (2, 4);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (2, 3);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (3, 4);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (3, 5);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (4, 4);
INSERT INTO meal_plan_sections (meal_plan_id_fk, section_id_fk) VALUES (4, 5);


-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************


CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

COMMIT TRANSACTION;