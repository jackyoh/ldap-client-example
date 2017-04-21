Run LDAP Server on Docker container for test
```
$ docker pull larrycai/openldap
$ docker run -d -p 389:389 --name ldap -t larrycai/openldap
$ docker exec -it ldap baseh
```

Generate password command
```
$ slappasswd
```

Add user to LDAP Server
user10.ldif
```
dn: uid=user10,ou=Users,dc=openstack,dc=org
objectClass: account
objectClass: posixAccount
cn: Users
userPassword: {SSHA}nbbQ6eq+Owel4iiuCd8N8fyzrhoYWwZX
uidNumber: 1001
gidNumber: 1001
homeDirectory: /home/user10
```

command
```
ldapadd -H ldap://localhost -x -D cn=admin,dc=openstack,dc=org -w password -c -f user10.ldif
```

group.ldif
```
dn: cn=admin3,ou=Users,dc=openstack,dc=org
cn: admin3
gidnumber: 10004
memberuid: user10
objectclass: posixGroup
objectclass: top
```
ldapadd -H ldap://localhost -x -D cn=admin,dc=openstack,dc=org -w password -c -f group4.ldif

Linux command line connect to LDAP command for test
```
# ldapsearch -x -D "uid=user10,ou=Users,dc=openstack,dc=org" -H ldap://192.168.1.13 -W -b "ou=Users,dc=openstack,dc=org" -s Sub "uid=user10"
```
