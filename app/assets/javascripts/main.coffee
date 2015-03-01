$(".options dt, .users dt").live "click", (e) ->
  e.preventDefault()
  if $(e.target).parent().hasClass("opened")
    $(e.target).parent().removeClass("opened")
  else
    $(e.target).parent().addClass("opened")
    $(document).one "click", ->
      $(e.target).parent().removeClass("opened")
  false