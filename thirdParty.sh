#!/bin/bash

wget -c https://johnvansickle.com/ffmpeg/releases/ffmpeg-release-amd64-static.tar.xz
tar Jxvf ffmpeg-release-amd64-static.tar.xz -C thirdParty/
mv thirdParty/ffmpeg-* thirdParty/ffmpeg
rm ffmpeg*tar.xz

wget -c https://exiftool.org/Image-ExifTool-12.05.tar.gz
tar -xzvf Image-ExifTool-12.05.tar.gz -C thirdParty/
cd thirdParty/Image-ExifTool-12.05/
perl Makefile.PL
mkdir ../exiftool
make test
make DESTDIR=../exiftool/ install
cd ..
rm -rf Image-ExifTool-12.05

mkdir -p thirdParty/imagemagick/
wget -c https://imagemagick.org/download/binaries/magick
mv magick thirdParty/imagemagick/magick

#sudo apt install -y libtesseract-dev automake ca-certificates g++ git libtool libleptonica-dev make pkg-config ibpango1.0-dev
#git clone https://github.com/tesseract-ocr/tesseract/
#cd tesseract/
#./autogen.sh
#./configure --prefix=/srv/fjala/PROG-102/PROG102-FileConversion/thirdParty/ocr
#make 
#make install
#ldconfig
#make training
#make training-install




