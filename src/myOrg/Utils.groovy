package myOrg;

class Utils {
    def steps
    Utils(steps) { this.steps = steps }

    def checkoutSCM() {
        steps.stage('Checkout SCM') {
            steps.deleteDir()
            steps.checkout steps.scm
        }
    }
}