def text = 'Dear "$firstname $lastname",\nSo nice to meet you in <% print city %>.\nSee you in ${month},\n${signed}'

def binding = ["firstname":"John", "lastname":"Smith", "city":"Boston", "month":"June", "signed":"Groovy-Dev"]
def binding2 = ["firstname":"Sam", "lastname":"Pullara", "city":"San Francisco", "month":"December", "signed":"Groovy-Dev"]

def engine = new groovy.text.SimpleTemplateEngine()
def template = engine.createTemplate(text)

println template.make(binding).toString()

println ""
println template.make(binding2).toString()