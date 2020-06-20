package myOrg;

class NpmTools {
    def steps
    NpmTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.sh "npm install"
        }
    }

    def build() {
        steps.stage('NPM Build') {
            steps.sh "npm run build"
        }
    }
}