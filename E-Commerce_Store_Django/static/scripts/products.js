$(document).ready(function(){

    // Product sorting System
    $("#sortSubmit").click(function(event){
        event.preventDefault();

        //Show the reset submissions button
        $("#reverseSubmit").removeClass();
        $("#reverseSubmit").addClass("btn btn-danger");

        $("#reverseSubmit").click(function(){
            //Hide the Reverse sumbission button
            $("#reverseSubmit").addClass("btn btn-danger d-none");

            $(".productItem").each(function(){
                $(this).css("display","block") ;
            });
        });


        //Get submission values
        var radioButtonValue = $("input[name='category']:checked").val();
        $("input[name='category']:checked").prop('checked', false) ; 

        var checkBoxButtonValues = [] ;

        $("input.gradeSort:checked").each(function(){
            checkBoxButtonValues.push($(this).val());
            $(this).prop('checked', false) ;
        });

        var priceMax = $("#maxPriceValue").val() ;
        var priceMin = $("#minPriceValue").val() ;

        $(".productItem").each(function(){
   
            //Clear previous submission
            if($(this).css("display") === "none"){
                $(this).css("display","block") ;
            };

            //Radio Button Sorting
            if(radioButtonValue != null && radioButtonValue !== '') {

                if ($(this).data('category') != radioButtonValue) {
                    $(this).css("display","none");
                };
            } ;

            //Checkbox Button Sorting
            if (checkBoxButtonValues.length  > 0){

                if(!(checkBoxButtonValues.includes($(this).data('grade')))) {
                    $(this).css("display","none");
                };
            };
 
    
        });
        
    });

    //Display Price Values
    function updateSliderValue(slider, displayElement) {
        $(displayElement).text($(slider).val());
    }

    // Initial display of the values
    updateSliderValue('#priceRangeMax', '#maxPriceValue');
    updateSliderValue('#priceRangeMin', '#minPriceValue');

    $('#priceRangeMax').on('input', function() {
        updateSliderValue(this, '#maxPriceValue');
    });

    $('#priceRangeMin').on('input', function() {
        updateSliderValue(this, '#minPriceValue');
    });

    // Add Product to CheckoutCart Models
})
