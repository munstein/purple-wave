package com.munstein.purple_wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.Type;

import com.munstein.purplewave.ScriptC_PurpleMachine;

/**
 * Created by @Munstein on 28/12/2017. --19:23
 */

public class PurpleCore {
    public static Bitmap Purple(Bitmap bitmap, Context context) {
        //Largura e altura da imagem
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //Bitmap de resultado
        Bitmap resultBitmap = bitmap.copy(bitmap.getConfig(), true);
        //Acesso ao contexto Renderscript
        RenderScript renderScript = RenderScript.create(context);
        //malloc no lado Java
        Allocation allocation = Allocation.createFromBitmap(renderScript, bitmap);
        Type type = allocation.getType();
        //Resultado das operações via renderscript
        Allocation result = Allocation.createTyped(renderScript, type);
        //Nosso script
        ScriptC_PurpleMachine purpleMachine = new ScriptC_PurpleMachine(renderScript);
        //Método Renderscript que será executado para todos o pixels da imagem
        //com o mesmo nome do método criado no PurpleMachine.rs
        purpleMachine.forEach_purpleProcess(allocation, result);
        //Enviando o resultado para o nosso Bitmap de resultado.
        result.copyTo(resultBitmap);
        //Obrigado pelos serviços prestados.
        result.destroy();
        allocation.destroy();
        purpleMachine.destroy();
        renderScript.destroy();
        //Pronto e filtrado!
        return resultBitmap;
    }
}
