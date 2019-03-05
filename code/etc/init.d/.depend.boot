TARGETS = console-setup mountkernfs.sh alsa-utils pidentd plymouth-log x11-common screen-cleanup pppd-dns hostname.sh apparmor dns-clean udev resolvconf mountdevsubfs.sh procps brltty hwclock.sh cryptdisks cryptdisks-early networking urandom checkroot.sh checkfs.sh rpcbind mountnfs-bootclean.sh mountnfs.sh bootmisc.sh kmod mountall-bootclean.sh mountall.sh checkroot-bootclean.sh
INTERACTIVE = console-setup udev cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
resolvconf: dns-clean
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
brltty: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
cryptdisks: checkroot.sh cryptdisks-early udev
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps dns-clean
urandom: hwclock.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
checkfs.sh: cryptdisks checkroot.sh
rpcbind: networking
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking rpcbind
bootmisc.sh: mountnfs-bootclean.sh udev mountall-bootclean.sh checkroot-bootclean.sh
kmod: checkroot.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
