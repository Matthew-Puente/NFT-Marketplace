# NFT-Marketplace

The Goal: To create a simple program using Spring Boot that can modify an upkeep an array that is being pushed to a web browser. The array itself holds the NFT's
which have their own data objects in them for differentiation. Additionally, the goal is to implement testing on the major functions of the program to ensure that
they run properly and effectively. 

The program can be broken up into several modules, The Controller, The Exception Handler, The Middleware Logging, and The Model modules.

The Controller handles most of the primary functions. It says which urls cause which information is displayed on screen, and handles any changes to the NFT list
    
   The Controller has 4 primary web mapping on it, the Get mapping, the Post mapping, the Put mapping and the Delete mapping.
      The Get mapping decides which urls return which nft, and if no NFT is specified by id, then it returns the whole list of NFTs
      The Post mapping allows you to add an NFT object to the list, and adds the NFT to the bottom of the list once it is created. 
      The Put mapping allows you to replace or update an existing NFT by putting in a new NFT object on a pre-existing ID
      The Delete mapping allows you to remove an NFT from the list of NFTs
      
The Exception Handler has one primary purpose, and it is to implement the 'NFT Not Found' exception, which will simply return the statement "NFT not found" if
that exception is thrown by the get mapping, should it be unable to find the NFT requested.

The Middleware Logging module allows interactions with the website to be logged and printed out into the Springboot runtime window. This allows us to keep track
of who interacts with the website and when.

Finally, the Model Module keeps a very simple data class for the NFT's themselves.

The next major section is the tests, which are all run through Junit. The tests are done in order to ensure that the list is manipulated the same way every time to 
guarantee that when the program is working expectedly the tests have the correct expected values.

The test themselves do the following in order: 

  1. Check that when the list is uploaded, the first object in the NFT array is the correct one.
  2. Creates a new NFT and adds it to the list, then checks if the list contains that NFT in the correct spot
  3. Changes an NFT then verifies that the changed NFT is in the correct position and has the correct changed values
  4. Removes an NFT then checks to make sure that NFT is no longer in the list
