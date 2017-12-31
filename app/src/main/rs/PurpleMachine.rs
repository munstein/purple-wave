#pragma version(1)
#pragma rs_fp_relaxed
#pragma rs java_package_name(com.munstein.purplewave)

uchar4 __attribute__((kernel)) purpleProcess(uchar4 in, uint32_t x, uint32_t y) {
    //Convertendo de uchar4 para float4
    float4 f4 = rsUnpackColor8888(in);

    //Acessamos os campos da varíavel, que representa um pixel,
    //atráves do seu RGB.
    return rsPackColorTo8888(f4.r, 0, f4.b, f4.a);
}
