window.addEventListener('load', ()=>{
	
	

		
		document.querySelectorAll('.deleteBtn').forEach(item=>{
			
			item.addEventListener('click', ()=>{
				
				console.log(item.value);
				
				var xhttp = new XMLHttpRequest();	
				
				xhttp.open("DELETE", "http://localhost:8080/orderpage" , true);
				
				xhttp.onreadystatechange  = function () {
					
						//xhttp.responseURL returns url of the response, final URL obtained after any redirects
			            let responseURL = xhttp.responseURL;
			            
			            //The Location.replace() method replaces the current resource with the one at the provided URL. 
			            //The difference from the assign() method is that after using replace() the current page will not 
			            //be saved in session History, meaning the user won't be able to use the back button to navigate to it.
			            window.location.replace(responseURL);
			       };
			       
				xhttp.send(item.value);
		
				
			})
			
		})
		
		
		

	
	
	
})

