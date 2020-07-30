# Store-API
<h4>Basic overview</h4>
<p>
1. This API was developed to meet the basic functions of a store<br>
2. It controls 4 entities: client, product, seller and shopping cart<br>
3. Endpoint controllers work on demand cache<br>
4. Each control has a List<> that is updated as the entities are persisted<br>
5. First the get methods look for the data in the List<><br>
If the data ins't there they look into the database<br>
6. Client and Seller models have a List of IDs Long and they are supose to keep the IDs os shopping carts<br>
So when a shopping cart is persisted the program must read the ID of the seller and the ID of client and find in database theirs data and update theirs shopping carts ID's List<br>
In that way the seller keeps tracks of all his sells and client of all his shopping carts in the store
</p>
<br>
<h4>Entites and pojos models properties</h4>
<ul>Client: Long id, String name, char genre, String email and ArrayList Long IDs</ul>
<ul>Product: Long id, String code, String name, String description, Double price, int quantity, Double weight and Double dimension</ul>
<ul>Seller: Long id, String name, String email, String password, char genre, LocalDate birthdate, Localdate companyStartDate and Arraylist Long IDs</ul>
<ul>Shopping Cart: Long id, Long seller's id, Long client's id, ArrayList of Sale </ul>
<ul> Sale POJO: Long productId and int productQuantity</ul>
<br>
<h4>Client Endpoints</h4>
<ul>Find all clients -> http://localhost:8080///store-api/client/findall</ul>
<ul>Find client by ID -> http://localhost:8080///store-api/client/findbyid/{id}</ul>
<ul>Find client by email -> http://localhost:8080///store-api/client/findbyemail/{email}</ul>
<ul>Find all clients by genre -> http://localhost:8080///store-api/client/findbygenre/{genre}</ul>
<ul>Save new client -> http://localhost:8080///store-api/client/save</ul>
<ul>Update client -> http://localhost:8080///store-api/client/update-alldata/{id}</ul>
<ul>Update client email -> http://localhost:8080///store-api/client/update-email/{id}</ul>
<br>
<h4>Product Endpoints</h4>
<ul>Find all products -> http://localhost:8080///store-api/product/findall</ul>
<ul>Find product by ID -> http://localhost:8080///store-api/product/findbyid/{id}</ul>
<ul>Find product by code (String) -> http://localhost:8080///store-api/product/findbycode/{code}</ul>
<ul>Save new product -> http://localhost:8080///store-api/product/save</ul>
<ul>Update product -> http://localhost:8080///store-api/product/update/{id}</ul>
<br>
<h4>Seller Endpoints</h4>
<ul>Find all sellers -> http://localhost:8080///store-api/seller/findall</ul>
<ul>Find seller by ID -> http://localhost:8080///store-api/seller/findbyid/{id}</ul>
<ul>Find seller by genre (char) -> http://localhost:8080///store-api/seller/findbygenre/{genre}</ul>
<ul>Save new seller -> http://localhost:8080///store-api/seller/save</ul>
<ul>Update seller -> http://localhost:8080///store-api/seller/update/{id}</ul>
<br>
<h4>Shopping Cart Endpoints</h4>
<ul>Find all shopping carts -> http://localhost:8080///store-api/shoppingcart/findall</ul>
<ul>Find shopping cart by ID -> http://localhost:8080///store-api/shoppingcart/findbyid/{id}</ul>
<ul>Save new shopping cart -> http://localhost:8080///store-api/shoppingcart/save</ul>
<ul>Update shopping cart -> http://localhost:8080///store-api/shoppingcart/update/{id}</ul>
<br>

