import jenkins.model.*
import hudson.security.*
import hudson.plugins.active_directory.*
   
   
   println "-->starting"
   // Check if enabled
def env = System.getenv()
if (!env['ADOP_LDAP_ENABLED'].toBoolean()) {
    println "--> ADOP LDAP Disabled"
    return
}
def instance = Jenkins.getInstance()
String domain = env['LDAP_ROOTDN']
String site = env['SITE']
String server = env['LDAP_SERVER']
String bindName = env['LDAP_MANAGER_DN']
String bindPassword = env['LDAP_MANAGER_PASSWORD']
adrealm = new ActiveDirectorySecurityRealm(domain, site, bindName, bindPassword, server,GroupLookupStrategy.RECURSIVE,true)
instance.setSecurityRealm(adrealm)


    // Save the state
    instance.save()
	println "-->Ending"