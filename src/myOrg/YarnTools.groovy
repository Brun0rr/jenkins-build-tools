package myOrg;

class YarnTools {
    def steps
    YarnTools(steps) { this.steps = steps }

    def install() {
        steps.stage('Get Dependencies') {
            steps.sh "yarn install"
        }
    }

    def build() {
        steps.stage('YARN Build') {
            steps.sh "yarn run build"
        }
    }
}