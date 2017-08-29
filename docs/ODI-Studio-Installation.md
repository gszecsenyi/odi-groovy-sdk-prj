## ODI Installation

#### Download
Download [Oracle Data Integrator 11gR1 11.1.1.9](http://www.oracle.com/technetwork/middleware/data-integrator/downloads/index.html) & 
Select - Download for All Platforms (including x64): Disk 1, Disk 2

Unzip both the archives

#### Installation
1. `Run as Admin` the right platform Setup exe/sh.
For a Win64 - _$DOWNLOADS/ofm_odi_generic_11.1.1.9.0_disk1_1of2\Disk1\install\win64\setup.exe_

   _Pay attention to the platform directory - as there are two setup.exe_

2. Specify JDK path
   
   _C:\Program Files\Java\jdk1.6.0_37_

3. Installer is launched

4. Welcome screen
   
   -> Next

5. Install Software Updates
   
   Skip Software Updates -> Next

6. Select Installation Type
   
   a. Developer installation (ODI Studio & ODI SDK)
   
   b. Standalone Installation -> Next

7. Prerequisite Checks
   
   -> Next

8. Specify Installation Location

   _C:\oracle\product\11.1.1\Oracle_ODI_1_ -> Next

9. Repository Configuration

   Skip Repository Configuration -> Next

9. Specify Agent Details

   Agent = ODIAgent  Port = 20910
   
   -> Next

9. Specify Security Updates

   Skip -> Next

9. Installation Summary

   Verify -> Install

9. Installation Progress

   Provide the location for Disk 2 - _$DOWNLOADS\ofm_odi_generic_11.1.1.9.0_disk1_2of2\Disk2_

9. Configuration Progress
   
   -> Next

9. Installation Completed
   
   -> Finish

