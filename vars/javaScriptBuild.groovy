import myOrg.NpmTools
import myOrg.YarnTools
import myOrg.Utils

def call(body) {
    def params = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    // Check required params
    def message = ''
    
    if (!params.package_manager) {
        currentBuild.result = 'FAILURE'
        message = 'javaScriptBuild => Missing required param: package_manager\n'
    }

    if (!params.package_manager.equals('yarn') && !params.package_manager.equals('npm')){
        currentBuild.result = 'FAILURE'
        message = 'javaScriptBuild => Invalid value for param: package_manager\nValue: ' + params.package_manager 
    }

    if (currentBuild.result.equals('FAILURE')) {
        error(message)
    }

    if (params.package_manager.equals('yarn')) {
        node('master') {
            def utils = new Utils(this)
            utils.checkoutSCM()
            
            def yarnTools = new YarnTools(this)
            yarnTools.install()
            yarnTools.build()
        }
    }
    if (params.package_manager.equals('npm')) { 
        node('master') {
            def utils = new Utils(this)
            utils.checkoutSCM()

            def npmTools = new NpmTools(this)
            npmTools.install()
            npmTools.build()
        }
    }
}
