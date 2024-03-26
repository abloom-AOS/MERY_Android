package com.abloom.mery.presentation.ui.category

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.abloom.mery.LoginDialogFragment
import com.abloom.mery.R
import com.abloom.mery.categorytest.CategoryViewModel
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.FragmentCategoryBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.util.onTabSelected
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category),
    CategoryRecyclerListener {

    companion object {

        private val FINANCE_TABITEM_POSION by lazy { 0 }
        private val COMMUNICATION_TABITEM_POSION by lazy { 1 }
        private val VALUES_TABITEM_POSION by lazy { 2 }
        private val LIFESTYLE_TABITEM_POSION by lazy { 3 }
        private val CHILD_TABITEM_POSION by lazy { 4 }
        private val FAMILY_TABITEM_POSION by lazy { 5 }
        private val SEX_TABITEM_POSION by lazy { 6 }
        private val HEALTH_TABITEM_POSION by lazy { 7 }
        private val WEDDING_TABITEM_POSION by lazy { 8 }
        private val FUTURE_TABITEM_POSION by lazy { 9 }
        private val PRESENT_TABITEM_POSION by lazy { 10 }
        private val PAST_TABITEM_POSION by lazy { 11 }
    }

    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()
    private var isLogin = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCategory.setOnNavigationClick {
            findNavController().popBackStack()
        }
        viewModel.isLoginCheck()
        setupIsLogin()
        setCategoryAdapter(isLogin)
        observeCategory()
        setupSelectedTabItem(args.category.categoryName)
        initListener()
    }



    private fun setupIsLogin() {
        viewModel.isLogin.observe(viewLifecycleOwner){
            when (it) {
                true -> {
                    binding.clNologin.visibility = View.INVISIBLE
                    isLogin=it
                }
                false -> {
                    binding.clNologin.visibility = View.VISIBLE
                    binding.tvLoginTag.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                    isLogin=it
                }
            }
        }
    }

    private fun setupSelectedTabItem(category: String) {

        when (category) {
            "finance" -> {
                selectTabItem(category, FINANCE_TABITEM_POSION)
            }

            "communication" -> {
                selectTabItem(category, COMMUNICATION_TABITEM_POSION)
            }

            "values" -> {
                selectTabItem(category, VALUES_TABITEM_POSION)
            }

            "lifestyle" -> {
                selectTabItem(category, LIFESTYLE_TABITEM_POSION)
            }

            "child" -> {
                selectTabItem(category, CHILD_TABITEM_POSION)
            }

            "family" -> {
                selectTabItem(category, FAMILY_TABITEM_POSION)
            }

            "sex" -> {
                selectTabItem(category, SEX_TABITEM_POSION)
            }

            "health" -> {
                selectTabItem(category, HEALTH_TABITEM_POSION)
            }

            "wedding" -> {
                selectTabItem(category, WEDDING_TABITEM_POSION)
            }

            "future" -> {
                selectTabItem(category, FUTURE_TABITEM_POSION)
            }

            "present" -> {
                selectTabItem(category, PRESENT_TABITEM_POSION)
            }

            "past" -> {
                selectTabItem(category, PAST_TABITEM_POSION)
            }
        }
    }

    private fun initListener() {

        binding.tvLoginTag.setOnClickListener {
            showLoginDialog()
        }


        binding.tb.onTabSelected {
            when (it.position) {
                FINANCE_TABITEM_POSION -> {
                    viewModel.requestQuestion("finance")
                }

                COMMUNICATION_TABITEM_POSION -> {
                    viewModel.requestQuestion("communication")
                }

                VALUES_TABITEM_POSION -> {
                    viewModel.requestQuestion("values")
                }

                LIFESTYLE_TABITEM_POSION -> {
                    viewModel.requestQuestion("lifestyle")
                }

                CHILD_TABITEM_POSION -> {
                    viewModel.requestQuestion("child")
                }

                FAMILY_TABITEM_POSION -> {
                    viewModel.requestQuestion("family")
                }

                SEX_TABITEM_POSION -> {
                    viewModel.requestQuestion("sex")
                }

                HEALTH_TABITEM_POSION -> {
                    viewModel.requestQuestion("health")
                }

                WEDDING_TABITEM_POSION -> {
                    viewModel.requestQuestion("wedding")
                }

                FUTURE_TABITEM_POSION -> {
                    viewModel.requestQuestion("future")
                }

                PRESENT_TABITEM_POSION -> {
                    viewModel.requestQuestion("present")
                }

                PAST_TABITEM_POSION -> {
                    viewModel.requestQuestion("past")
                }
            }
        }
    }

    private fun observeCategory() {
        viewModel.questions.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it)

        }
    }

    private fun setCategoryAdapter(isLogin: Boolean) {
        categoryAdapter = CategoryAdapter(this)
        val categoryManager = object : LinearLayoutManager(requireActivity(), VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return isLogin
            }
        }
        binding.rv.apply {
            layoutManager = categoryManager
            adapter = categoryAdapter
            scrollToPosition(0)
        }
    }

    private fun selectTabItem(category: String, index: Int) {
        lifecycleScope.launch {
            binding.tb.selectTab(binding.tb.getTabAt(index))
            lifecycleScope.launch(Dispatchers.Main) {
                binding.tb.setScrollPosition(index, 0f, false)
            }
        }
        viewModel.requestQuestion(category)
    }

    override fun onCategoryItemClick(question: Question) {
        if (isLogin) {
            val action =
                CategoryFragmentDirections.actionGlobalWriteAnswerFragment(question.questionId)
            findNavController().navigate(action)
        }
    }

    private fun showLoginDialog() {
        val bottomSheetFragment = LoginDialogFragment()
        bottomSheetFragment.show(requireActivity().supportFragmentManager, LoginDialogFragment().tag)
    }
}
