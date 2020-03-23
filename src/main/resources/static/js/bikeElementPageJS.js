window.addEventListener("load", ()=>{
	
	//list of all checkbox input elements
	var checkboxList = document.querySelectorAll(".checkboxInput");
	
	//currently checked input
	var checkedElement = null;
	
	//any element is currently checked
	var checked = false;
	
	//event listener to each element of checkboxList
	checkboxList.forEach((item)=>{
		item.addEventListener("click", ()=>{
			
			//when page is loaded there's no checked element
			//when user switches between elements, previous element will be unchecked and new element set as currently checked
			if(checkedElement!==null && checkedElement!==item){
				checkedElement.checked = false;
			}
			
			checked=!checked;
			
			checkedElement=item;
			
		});
	});
	
	
	//user can't submit form until some element is checked
	document.querySelector("#submitBtn").addEventListener("click", (event)=>{
		
		if(checked === false){
			event.preventDefault();
		}
		
	});
	
	
})