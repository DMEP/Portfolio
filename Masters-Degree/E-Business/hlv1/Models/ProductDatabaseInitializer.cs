using System.Collections.Generic;
using System.Data.Entity;

namespace hlv1.Models
{
    // Database set to create always for testing, set to when model changes for real world version
    public class ProductDatabaseInitializer : DropCreateDatabaseAlways<ProductContext>
    {
        protected override void Seed(ProductContext context)
        {
            GetCategories().ForEach(c => context.Categories.Add(c));
            GetProducts().ForEach(p => context.Products.Add(p));
        }
        // Set the categories of the database
        private static List<Category> GetCategories()
        {
            var categories = new List<Category> {
                new Category
                {
                    CategoryID = 1,
                    CategoryName = "Accessories",
                    Description = "Brushes, Chalk, Tape",
                    CatImagePath="Brushes1.jpg"
                },
                new Category
                {
                    CategoryID = 2,
                    CategoryName = "Ascenders, Belays & Descenders",
                    Description = "Ascenders, Belays & Descenders",
                    CatImagePath="Ascender1.jpg"
                },
                new Category
                {
                    CategoryID = 3,
                    CategoryName = "Bags",
                    Description = "Rucksacks, Rope Bags",
                    CatImagePath="RopeBag1.jpg"
                },
                new Category
                {
                    CategoryID = 4,
                    CategoryName = "Bouldering Pads",
                    Description = "Bouldering Pads",
                    CatImagePath="Boulderpad1.jpg"
                },
                new Category
                {
                    CategoryID = 5,
                    CategoryName = "Cams",
                    Description = "Cams",
                    CatImagePath="Cam1.jpg"
                },
                new Category
                {
                    CategoryID = 6,
                    CategoryName = "Carabiners",
                    Description = "Carabiners",
                    CatImagePath="Carabiner1.jpg"
                },
                new Category
                {
                    CategoryID = 7,
                    CategoryName = "Climbing Holds",
                    Description = "Climbing Holds",
                    CatImagePath="Climbinghold1.jpg"
                },
                new Category
                {
                    CategoryID = 8,
                    CategoryName = "Clothing",
                    Description = "Beanies, Caps, T-Shirts",
                    CatImagePath="Chest1.jpg"
                },
                new Category
                {
                    CategoryID = 9,
                    CategoryName = "Footwear",
                    Description = "Approach, Climbing, Mountain",
                    CatImagePath="Approach1.jpg"
                },
                new Category
                {
                    CategoryID = 10,
                    CategoryName = "Harness",
                    Description = "Harnesses",
                    CatImagePath="Harness1.jpg"
                },
                new Category
                {
                    CategoryID = 11,
                    CategoryName = "Headgear",
                    Description = "Helmets",
                    CatImagePath="Helmet1.jpg"
                },
                new Category
                {
                    CategoryID = 12,
                    CategoryName = "Pulley & Slings",
                    Description = "Pulleys & Slings",
                    CatImagePath="Pulley1.jpg"
                },
                new Category
                {
                    CategoryID = 13,
                    CategoryName = "Quickdraws",
                    Description = "Quickdraws",
                    CatImagePath="Quickdraw1.jpg"
                },
                new Category
                {
                    CategoryID = 14,
                    CategoryName = "Rope",
                    Description = "Ropes",
                    CatImagePath="Rope1.jpg"
                },
                new Category
                {
                    CategoryID = 15,
                    CategoryName = "Training",
                    Description = "Training Equipment",
                    CatImagePath="Training1.jpg"
                },
                new Category
                {
                    CategoryID = 16,
                    CategoryName = "Wires & Nuts",
                    Description = "Wire & Nuts",
                    CatImagePath="Nut1.jpg"
                }
            };
            return categories;
        }
        // Set the products of the database
        private static List<Product> GetProducts()
        {
            var products = new List<Product> 
            {
                // Acessories Products
                new Product 
                {
                    ProductID = 1,
                    ProductName = "Coloured Brushes",
                    Description = "Coloured set of brushes for climbing holds.",
                    ImagePath="Brushes1.jpg",
                    UnitPrice = 3.95,
                    CategoryID = 1
               },
               new Product
               {
                    ProductID = 2,
                    ProductName = "Metolius Chalk",
                    Description = "Large bag of Metolius super chalk.", 
                    ImagePath="Chalk1.jpg",
                    UnitPrice = 9.50,
                    CategoryID = 1
               },
               new Product
               {
                   ProductID = 3,
                   ProductName = "Climb On Creams",
                   Description = "Aftercare cream for climbers reecovering from skin injuries.",
                   ImagePath="Cream1.jpg",
                   UnitPrice = 4.99,
                   CategoryID = 1
               },
               new Product
               {
                   ProductID = 4,
                   ProductName = "Crimp Oil",
                   Description = "Aftercare oil for climbers recovering from finger injuries.",
                   ImagePath="Oil1.jpg",
                   UnitPrice = 8.95,
                   CategoryID = 1
               },
               new Product
               {
                   ProductID = 5,
                   ProductName = "Metolius Climbing Tape",
                   Description = "100% cotton, 1 1/ inch wide by 45 foot athletic tape.",
                   ImagePath="Tape1.jpg",
                   UnitPrice = 5.00,
                   CategoryID = 1
               },
               new Product
               {
                   ProductID = 6,
                   ProductName = "Climb On Wax",
                   Description = "Aftercare wax.",
                   ImagePath="Wax1.jpg",
                   UnitPrice = 4.99,
                   CategoryID = 1
               },
               // Ascender, Belay & Descender Products
               new Product
               {
                   ProductID = 7,
                   ProductName = "LIFT",
                   Description = "LIFT RIGHT hand ascender for safe and smooth ascent with extra-wide handle opening to accomodate gloved hands.",
                   ImagePath="Ascender1.jpg",
                   UnitPrice = 34.95,
                   CategoryID = 2
               },
               new Product
               {
                   ProductID = 8,
                   ProductName = "ATC Belay",
                   Description = "The ATC belay is a lightweight, simple easy to operate design.",
                   ImagePath="Belay1.jpg",
                   UnitPrice = 20.00,
                   CategoryID = 2
               },
               new Product
               {
                   ProductID = 9,
                   ProductName = "Super 8 Rappel",
                   Description = "Figure of 8 descender.",
                   ImagePath="Descender1.jpg",
                   UnitPrice = 10.00,
                   CategoryID = 2
               },
               // Bags Products
               new Product
               {
                   ProductID = 10,
                   ProductName = "La Sportiva Chalk Bag",
                   Description = "An all purpose chalk bag with drawstring closure, 6.5 inch tall by 5 inch wide by 5 inch deep.",
                   ImagePath="Chalkbag1.jpg",
                   UnitPrice = 25.00,
                   CategoryID = 3
               },
               new Product
               {
                   ProductID = 11,
                   ProductName = "8b+ Pam",
                   Description = "Chalk bag 8b+ Pam which contains brush compartment.",
                   ImagePath="Chalkbag2.jpg",
                   UnitPrice = 26.00,
                   CategoryID = 3
               },
               new Product
               {
                   ProductID = 12,
                   ProductName = "Prana Chalk Bag",
                   Description = "The Prana chalk back.",
                   ImagePath="Chalkbag3.jpg",
                   UnitPrice = 29.00,
                   CategoryID = 3
               },
               new Product
               {
                   ProductID = 13,
                   ProductName = "Deuter Rope Bag",
                   Description = "Sturdy bag for transport and protection of climbing ropes and shoes.",
                   ImagePath="Ropebag1.jpg",
                   UnitPrice = 35.00,
                   CategoryID = 3
               },
               new Product
               {
                   ProductID = 14,
                   ProductName = "Haglofs Roc Rescue 40 ",
                   Description = "Roc Rescue 40 developed to be one of the most durable and reliable alpine climbing pack. Desinged with robust materials and smart details.",
                   ImagePath="Rucksack1.jpg",
                   UnitPrice = 120.00,
                   CategoryID = 3
               },
               // Boulder Pads Products
               new Product
               {
                   ProductID = 15,
                   ProductName = "DMM Spot 2 Bouldering Pad",
                   Description = "The DMM Spot 2 is a small/medium crash pad.",
                   ImagePath="Boulderpad1.jpg",
                   UnitPrice = 99.95,
                   CategoryID = 4
               },
               // Cams Products
               new Product
               {
                   ProductID = 16,
                   ProductName = "CT Cam",
                   Description = "The Climbing Technology Cam is the latest in lightweight and durable materials with an ergonomic and contoured handle for easy use when climbing",
                   ImagePath="Cam1.jpg",
                   UnitPrice = 136.00,
                   CategoryID = 5
               },
               // Carabiners Products
               new Product 
               {
                   ProductID = 17,
                   ProductName = "Black Diamond Carabiner",
                   Description = "The Black Diamond carabiner is a rock lock, twist lock carabiner.",
                   ImagePath="Carabiner1.jpg",
                   UnitPrice = 19.95,
                   CategoryID = 6
               },
               // Climbing Holds Products
               new Product
               {
                   ProductID = 18,
                   ProductName = "Coloured Climbing holds",
                   Description = "12 coloured climbing holds", 
                   ImagePath="Climbinghold1.jpg",
                   UnitPrice = 65.00,
                   CategoryID = 7
               },
               // Clothing Products 
               new Product
               {
                   ProductID = 19,
                   ProductName = "123t EAT SLEEP CLIMB",
                   Description = "123t EAT SLEEP CLIMB loose fit t-shirt",
                   ImagePath="Chest1.jpg",
                   UnitPrice = 8.99,
                   CategoryID = 8
               },
               new Product
               {
                   ProductID = 20,
                   ProductName = "Denim climbing Pants",
                   Description = "Wear-resistant, breathable, fashion and professional trousers",
                   ImagePath="Legs1.jpg",
                   UnitPrice = 34.95,
                   CategoryID = 8
               },
               new Product
               {
                   ProductID = 21,
                   ProductName = "PMI Fingerless Belay Gloves",
                   Description = "PMI Fingerless Belay Gloves provide just enough coverage to protect your hands while still allowing excellent dexterity",
                   ImagePath="Gloves1.jpg",
                   UnitPrice = 15.00,
                   CategoryID = 8
               },
               // Footwear Products 
               new Product
               {
                   ProductID = 22,
                   ProductName = "Salewa Mountain Trainer",
                   Description = "The Salewa Mountain Trainer is an approach shoe fitted with multi-fit footbed and the 3F system Evo.",
                   ImagePath="Approach1.jpg",
                   UnitPrice = 149.99,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 23,
                   ProductName = "Mammut Monolith GTX",
                   Description = "The Mammut Monolith GTX Apline Trekking Boot is a light and comfortable .",
                   ImagePath="Mountain1.jpg",
                   UnitPrice = 134.95,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 24,
                   ProductName = "Anasazi 5.10 VCS",
                   Description = "The Anasazi 5.10 VSC has a slingshot rand, dual pull-on loops and a asymmetric toe. The lined Cowdura synthetic upper and the sole is Stealth ONYXX ",
                   ImagePath="Climbing1.jpg",
                   UnitPrice = 120.00,
                   CategoryID = 9
               },
                new Product
               {
                   ProductID = 25,
                   ProductName = "Climb X Technician",
                   Description = "The Climb X Technician has a slingshot rand, molded toes cap and super sticky X-Factor rubber and rubberized straps.",
                   ImagePath="Climbing2.jpg",
                   UnitPrice = 60.00,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 26,
                   ProductName = "Elerid Typhoon",
                   Description = "The Elerid Typoon is built on a downturned last supported by the E-Grip sole allowing for greater sensitivity.",
                   ImagePath="Climbing3.jpg",
                   UnitPrice = 80.00,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 27,
                   ProductName = "Evolv Bandit",
                   Description = "The Evolv Bandit is an asymmetrical toe with elastic Synthratek upper and high riction ruber sole.",
                   ImagePath="Climbing4.jpg",
                   UnitPrice = 85.00,
                   CategoryID = 9
               },
                new Product
               {
                   ProductID = 28,
                   ProductName = "Evolv Sharman",
                   Description = "Designed by Chis Sharman, incorpating uniques material, a new designed last and exclusive technologies.",
                   ImagePath="Climbing5.jpeg",
                   UnitPrice = 149.99,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 29,
                   ProductName = "Evolv Talon",
                   Description = "The Evolv Talon is a high performance shoe with high friction rubber sole a non-stretch synthratek synthetic upper.",
                   ImagePath="Climbing6.jpg",
                   UnitPrice = 34.95,
                   CategoryID = 9
               },
               new Product
               {
                   ProductID = 30,
                   ProductName = "La Sportiva Katana Lace",
                   Description = "La Sportiva Katana Lace one of the most successful climbing shoes. It is slightly asymmetric and slightly downturned.",
                   ImagePath="Climbing7.jpg",
                   UnitPrice = 20.00,
                   CategoryID = 9
               },
               // Harness Products
               new Product
               {
                   ProductID = 31,
                   ProductName = "Black Diamond Momentum AL Climbing Harness",
                   Description = "The Black Diamond Momentum AL climbing harnessos a traditinal, fold-back waistbelt buckle with adjutable leg loops.",
                   ImagePath="Harness1.jpg",
                   UnitPrice = 45.00,
                   CategoryID = 10
               },
               // Headwear Products
               new Product
               {
                   ProductID = 32,
                   ProductName = "Edelrid Rope Beanie Light",
                   Description = "The Edelrid Rope Beanie Light, lightweight and durable.",
                   ImagePath="Beanie1.jpg",
                   UnitPrice = 25.00,
                   CategoryID = 11
               },
               new Product
               {
                   ProductID = 33,
                   ProductName = "Edelrid Cuba Cap",
                   Description = "The Edelrid Cuba Cap, lightweight and durable.",
                   ImagePath="Cap1.jpg",
                   UnitPrice = 25.00,
                   CategoryID = 11
               },
               new Product
               {
                   ProductID = 34,
                   ProductName = "Mammut El Cap Climbing Helmet",
                   Description = "The Mammut El Cap climbing helmet is a hard plastic shell with 2K-EPS foam lining.",
                   ImagePath="Helmet1.jpg",
                   UnitPrice = 70.00,
                   CategoryID = 11
               },
               new Product
               {
                   ProductID = 35,
                   ProductName = "Petzl Pixa 3 Head Tourch",
                   Description = "The Petzl Pixa 3 is constructed from high quality materials and a clever design to ensure that you always see what you are looking at.",
                   ImagePath="Headtorch1.jpg",
                   UnitPrice = 69.00,
                   CategoryID = 11
               },
               // Pulley & Slings Products
               new Product
               {
                   ProductID = 36,
                   ProductName = "CAMP Pulley",
                   Description = "CAMP small fixed pulley for a fast speed system with brash bushing and permanent rivet that never loosens.",
                   ImagePath="Pulley1.jpg",
                   UnitPrice = 35.00,
                   CategoryID = 12
               },
               new Product
               {
                   ProductID = 37,
                   ProductName = "Rock Empire Sling",
                   Description = "16mm nylon sling by Rock Empire. Set of 3.",
                   ImagePath="Sling1.jpg",
                   UnitPrice = 15.00,
                   CategoryID = 12
               },
               // Quickdraws Products
               new Product
               {
                   ProductID = 38,
                   ProductName = "Black Diamond Live Wire Quickdraw",
                   Description = "The Black Diamond Love Wire, lightweight and durable for easy climbing.",
                   ImagePath="Quickdraw1.jpg",
                   UnitPrice = 42.95,
                   CategoryID = 13
               },
               // Rope Products
               new Product
               {
                   ProductID = 39,
                   ProductName = "Climbing Rope",
                   Description = "Climbing rope with a length of 200 feet.",
                   ImagePath="Rope1.jpg",
                   UnitPrice = 250.00,
                   CategoryID = 14
               },
               // Training Products
               new Product
               {
                   ProductID = 40,
                   ProductName = "Beastmaker",
                   Description = "The Beastmaker finger strength training board.",
                   ImagePath="Training1.jpg",
                   UnitPrice = 75.00,
                   CategoryID = 15
               },
               // Wire/Nuts Products
               new Product
               {
                   ProductID = 41,
                   ProductName = "Metolius Ultralight Curve Nut",
                   Description = "Metolius Ultralight Curve Nut is 30% lighter than competition combing the stability of 3-point contact with the ease of a straight-sided taper.",
                   ImagePath="Nut1.jpg",
                   UnitPrice = 149.95,
                   CategoryID = 16
               }
            };
            return products;
        }
    }
}