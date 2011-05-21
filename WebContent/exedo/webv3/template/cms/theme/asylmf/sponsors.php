<div class="squarebanner">
    <h2 class="sidetitl">广告赞助</h2>
    <ul>
        <li>
        <?php 
            $ban1 = get_option('asylmf_banner1'); 
            $url1 = get_option('asylmf_url1'); 
            $lab1 = get_option('asylmf_lab1'); 
            $alt1 = get_option('asylmf_alt1');
        ?>
        <a href="<?php echo ($url1); ?> " title="<?php echo ($lab1); ?>" target="_blank"><img src="<?php echo ($ban1); ?>" alt="<?php echo ($alt1); ?>" style="vertical-align:bottom;" /></a>
        </li>			
        
        <li>
        <?php 
            $ban2 = get_option('asylmf_banner2'); 
            $url2 = get_option('asylmf_url2'); 
            $lab2 = get_option('asylmf_lab2'); 	
            $alt2 = get_option('asylmf_alt2');
        ?>
        <a href="<?php echo ($url2); ?>" title="<?php echo ($lab2); ?>" target="_blank"><img src="<?php echo ($ban2); ?>" alt="<?php echo ($alt2); ?>" style="vertical-align:bottom;" /></a>
        </li>
        
        <li>
        <?php 
            $ban3 = get_option('asylmf_banner3'); 
            $url3 = get_option('asylmf_url3'); 
            $lab3 = get_option('asylmf_lab3'); 
            $alt3 = get_option('asylmf_alt3');	
        ?>
        <a href="<?php echo ($url3); ?>" title="<?php echo ($lab3); ?>" target="_blank"><img src="<?php echo ($ban3); ?>" alt="<?php echo ($alt3); ?>" style="vertical-align:bottom;" /></a>
        </li>
        
        <li>
        <?php 
            $ban4 = get_option('asylmf_banner4'); 
            $url4 = get_option('asylmf_url4');
            $lab4 = get_option('asylmf_lab4'); 
            $alt4 = get_option('asylmf_alt4');	
        ?>
        <a href="<?php echo ($url4); ?>" title="<?php echo ($lab4); ?>" target="_blank"><img src="<?php echo ($ban4); ?>" alt="<?php echo ($alt4); ?>" style="vertical-align:bottom;" /></a>
        </li>
    </ul>
</div>